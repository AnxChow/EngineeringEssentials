/**
 * Copyright 2017 Goldman Sachs.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class will define a company and its attributes
 * Look at resources/data/companyInfo.json
 */

public class Company {

    @JsonProperty
    private String symbol;
    @JsonProperty
    private String name;
    @JsonProperty
    private String headquartersCity;
    @JsonProperty
    private String headquartersStateOrCountry;
    @JsonProperty
    private int numberOfEmployees;
    @JsonProperty
    private String sector;
    @JsonProperty
    private String industry;

    public String getSymbol(){
      return symbol;
    }

    public String getName(){
      return name;
    }

    // TODO - Think back to your modelling session
    // Define the attributes of a Company based on the
    // provided data in resources/data

    // TODO - add getter and setter methods for your attributes
}
