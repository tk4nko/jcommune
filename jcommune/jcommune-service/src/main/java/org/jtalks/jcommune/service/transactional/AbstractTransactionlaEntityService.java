/**
 * Copyright (C) 2011  jtalks.org Team
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
 * Also add information on how to contact you by electronic and paper mail.
 * Creation date: Apr 12, 2011 / 8:05:19 PM
 * The jtalks.org Project
 */
package org.jtalks.jcommune.service.transactional;

import org.jtalks.jcommune.model.dao.Dao;
import org.jtalks.jcommune.model.entity.Persistent;
import org.jtalks.jcommune.service.EntityService;

import java.util.List;

/**
 * Generic implementation of all entity based services.
 * Most of the implementations of the methods are basing on straightforward calls
 * of the same named method from DAO interface.
 *
 * @author Osadchuck Eugeny
 * @author Kirill Afonin
 */
public abstract class AbstractTransactionlaEntityService<T extends Persistent>
        implements EntityService<T> {
    /**
     * Dao object implementation.
     */
    private Dao<T> dao;


    /**
     * Create an instance of entity based service
     *
     * @param dao - data access object, which should be able do all CRUD operations.
     */
    public AbstractTransactionlaEntityService(Dao<T> dao) {
        this.dao = dao;
    }

    /**
     * Get data access object which implements data manipulation for entity T.
     *
     * @return - data access object which implements data manipulation for entity T.
     */
    public Dao<T> getDao() {
        return dao;
    }

    /**
     * Set data access object, which implements data manipulation for entity T
     *
     * @param dao - data access object which implements data manipulation for entity T.
     */
    public void setDao(Dao<T> dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(Long id) {
        return dao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() {
        return dao.getAll();
    }

}
