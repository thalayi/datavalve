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

import org.fluttercode.datavalve.provider.ParameterizedDataProvider;


/**
 * Dataset that accepts any implementation of {@link ParameterizedDataProvider} as its
 * provider. Used as a helper class instead of using a {@link Dataset} with a
 * generic type parameter for passing in the specific type.
 * 
 * 
 * @author Andy Gibson
 * 
 */
public class ParameterizedDataset<T> extends Dataset<T,ParameterizedDataProvider<T>>  {

	private static final long serialVersionUID = 1L;
	
	public ParameterizedDataset(ParameterizedDataProvider<T> provider) {
		super(provider);
	}	
}
