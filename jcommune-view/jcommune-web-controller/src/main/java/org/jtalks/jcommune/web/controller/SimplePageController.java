/**
 * Copyright (C) 2011  JTalks.org Team
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.jtalks.jcommune.web.controller;

import org.jtalks.jcommune.model.entity.SimplePage;
import org.jtalks.jcommune.service.SimplePageService;
import org.jtalks.jcommune.service.dto.SimplePageInfoContainer;
import org.jtalks.jcommune.service.exceptions.NotFoundException;
import org.jtalks.jcommune.web.dto.SimplePageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


/**
 * Controller for simple pages
 *
 * @author Scherbakov Roman
 * @author Alexander Gavrikov
 */

@Controller
public class SimplePageController {

    private static final String PAGE_DTO = "simplePageDto";
    private static final String PAGE_PATH_NAME = "pagePathName";

    private SimplePageService simplePageService;

    /**
     * This method turns the trim binder on. Trim binder
     * removes leading and trailing spaces from the submitted fields.
     * So, it ensures, that all validations will be applied to
     * trimmed field values only.
     *
     * @param binder    Binder object to be injected
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    /**
     * @param simplePageService the object which provides actions on {@link SimplePage} entity
     */
    @Autowired
    public SimplePageController(SimplePageService simplePageService) {
        this.simplePageService = simplePageService;
    }

    /**
     * Show a page information by its path name.
     *
     * @param pagePathName              address in browser which associated with current simple page
     * @return {@code ModelAndView}     object with 'simplePage' view with data from {@link SimplePageDto}
     * @throws NotFoundException        when page was not found or was not exist
     */
    @RequestMapping(value = "/pages/{pagePathName}", method = RequestMethod.GET)
    public ModelAndView showPage(@PathVariable(PAGE_PATH_NAME) String pagePathName) throws NotFoundException {
        SimplePage page = simplePageService.getPageByPathName(pagePathName);
        
        SimplePageDto pageDto = new SimplePageDto(page);
        return new ModelAndView("simplePage")
                .addObject(PAGE_DTO, pageDto)
                .addObject("simplePage", page);
    }


    /**
     * Show a form in browser for edit page information and content in {@link SimplePage} entity
     * associated with address in browser
     *
     * @param pagePathName              address in browser which associated with current {@link SimplePage}
     * @return {@code ModelAndView}     object with 'simplePageEditor' view filled with data from {@link SimplePageDto}
     * @throws NotFoundException        when page was not found or not exist
     */
    @RequestMapping(value = "/pages/{pagePathName}/edit", method = RequestMethod.GET)
    public ModelAndView showEditPage(@PathVariable(PAGE_PATH_NAME) String pagePathName) throws NotFoundException {
        SimplePage page = simplePageService.getPageByPathName(pagePathName);

        SimplePageDto pageDto = new SimplePageDto(page);

        return new ModelAndView("simplePageEditor")
                .addObject(PAGE_DTO, pageDto);
    }

    /**
     * Change name or/and content of page associated with pagePathName
     *
     * @param simplePageDto             Dto with entered data
     * @param result                    Validation result
     * @param pagePathName              address in browser which associated with current simple page
     * @return {@code ModelAndView}     object with redirect to edited page if saved successfully
     *                              or show form with error message
     *
     * @throws NotFoundException when page was not found or not exist
     */
    @RequestMapping(value = "/pages/{pagePathName}/edit", method = RequestMethod.POST)
    public ModelAndView update(@Valid @ModelAttribute SimplePageDto simplePageDto,
                               BindingResult result,
                               @PathVariable(PAGE_PATH_NAME) String pagePathName) throws NotFoundException {
        if (result.hasErrors()) {
            return new ModelAndView("simplePageEditor")
                    .addObject(PAGE_PATH_NAME, pagePathName);
        }
        SimplePageInfoContainer simplePageInfoContainer = simplePageDto.getSimplePageInfoContainer();
        simplePageService.updatePage(simplePageInfoContainer);

        return new ModelAndView("redirect:/pages/" + pagePathName);
    }

}
