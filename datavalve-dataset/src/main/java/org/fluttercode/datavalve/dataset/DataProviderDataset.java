/*
 * Copyright 2010, Andrew M Gibson
 *
 * www.andygibson.net
 *
 * This file is part of DataValve.
 *
 * DataValve is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * DataValve is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with DataValve.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.fluttercode.datavalve.dataset;

import org.fluttercode.datavalve.DataProvider;

/**
 * Concrete instance of the {@link Dataset} that fixes the
 * generic provider type to a simple {@link DataProvider} so it can be used with
 * any class that implements the {@link DataProvider} interface.
 * 
 * @author Andy Gibson
 * 
 * @param <T>
 *            type of object returned from the provider
 */
public class DataProviderDataset<T> extends
		Dataset<T, DataProvider<T>> {

	private static final long serialVersionUID = 1L;

	public DataProviderDataset(DataProvider<T> provider) {
		super(provider);
	}

}
