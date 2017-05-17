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

package resources;

import pojo.Stock;
import utility.FileHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

// TODO - add your @Path here
@Path("stock")
public class StockResource {

    // TODO - Add a @GET resource to get stock data
    // Your service should return data based on 3 inputs
    // Stock ticker, start date and end date

    @GET
    @Path("/{ticker}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getData(@PathParam("ticker") String ticker) throws IOException {
        //Map<String, Double> price = new Map<String, Double>();
        List<Stock> allData = FileHelper.readAllStockData("/Users/ankita/Documents/Self/cs/GSEE/EngineeringEssentials/caseStudy/services/src/main/resources/data/historicalStockData.json");
        Optional<Stock> findStock = allData.stream()
                .filter(stock -> ticker.equalsIgnoreCase(stock.getName()))
                .findFirst();
        Stock stock = findStock.orElseThrow(() -> new NotFoundException("Ticker not found"));
        return Response.status(Response.Status.OK).entity(stock).build();
    }

    @GET
    @Path("/{ticker}/{start}/{end}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getData(@PathParam("ticker") String ticker, @PathParam("start") String start, @PathParam("end") String end) throws IOException {
        List<Map<String, Double>> list = new ArrayList<Map<String, Double>>();
        Map<String, Double> price = new LinkedHashMap<String, Double>();
        boolean found = false;
        List<Stock> allData = FileHelper.readAllStockData("/Users/ankita/Documents/Self/cs/GSEE/EngineeringEssentials/caseStudy/services/src/main/resources/data/historicalStockData.json");
        for (Stock x : allData) { //go through all data
            if (x.getName().equals(ticker)) { //find stock we want
                for (Map<String, Double> y : x.getDailyClosePrice()) { //go through every map in list [just 1]

                    for (Map.Entry<String, Double> entry : y.entrySet()) { //for entry in map
                        if (entry.getKey().equals(start)) { //if its the start values
                            price.put(entry.getKey(), entry.getValue()); //add it to the new price map
                            found = true;
                        }
                        if (found) { //if true keep going
                            price.put(entry.getKey(), entry.getValue()); //add to new price map
                            if (entry.getKey().equals(end)) { //if found end
                                found = false;
                                break; //break
                            }
                        }
                        else {
                            continue;
                        }
                    }
                }
            }
            else {
                continue;
            }
        }
        list.add(price);
        Stock hold = new Stock();
        hold.setName(ticker);
        hold.setDailyClosePrice(list);

        return Response.status(Response.Status.OK).entity(hold).build();
    }

    @GET
    @Path("/{ticker}/{start}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getData(@PathParam("ticker") String ticker, @PathParam("start") String start) throws IOException {
        List<Map<String, Double>> list = new ArrayList<Map<String, Double>>();
        Map<String, Double> price = new LinkedHashMap<String, Double>();
        boolean found = false;
        List<Stock> allData = FileHelper.readAllStockData("/Users/ankita/Documents/Self/cs/GSEE/EngineeringEssentials/caseStudy/services/src/main/resources/data/historicalStockData.json");
        for (Stock x : allData) { //go through all data
            if (x.getName().equals(ticker)) { //find stock we want
                for (Map<String, Double> y : x.getDailyClosePrice()) { //go through every map in list [just 1]

                    for (Map.Entry<String, Double> entry : y.entrySet()) { //for entry in map
                        if (entry.getKey().equals(start)) { //if its the start values
                            price.put(entry.getKey(), entry.getValue()); //add it to the new price map
                            found = true;
                        }
                        if (found) { //if true keep going
                            price.put(entry.getKey(), entry.getValue()); //add to new price map
                        }
                        else {
                            continue;
                        }
                    }
                }
            } else {
                continue;
            }
        }
        list.add(price);
        Stock hold = new Stock();
        hold.setName(ticker);
        hold.setDailyClosePrice(list);

        return Response.status(Response.Status.OK).entity(hold).build();
    }
}
//
