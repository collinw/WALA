/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.wala.ipa.callgraph.propagation;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.types.TypeReference;

/**
 * An {@link InstanceKey} which represents the constant char[] contents
 * of a string constant object.
 * 
 * @author sjfink
 */
public class StringConstantCharArray implements InstanceKey {
  
  private final ConstantKey<String> constant;
  
  private StringConstantCharArray(ConstantKey<String> constant) {
    this.constant = constant;
  }
  
  public static StringConstantCharArray make(ConstantKey<String> constant) {
    return new StringConstantCharArray(constant);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((constant == null) ? 0 : constant.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final StringConstantCharArray other = (StringConstantCharArray) obj;
    if (constant == null) {
      if (other.constant != null)
        return false;
    } else if (!constant.equals(other.constant))
      return false;
    return true;
  }

  public IClass getConcreteType() {
    return constant.getConcreteType().getClassHierarchy().lookupClass(TypeReference.CharArray);
  }

  @Override
  public String toString() {
    return "StringConstantCharArray:" + constant;
  }
}