/*
 * Copyright 2021 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.dgsjava16maven;

import com.example.dgsjava16maven.records.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class ShowsDataFetcher {

    private final List<Show> showData = List.of(
            new Show(1, "Stringer Things", 2016),
                new Show(2, "Ozark", 2017)
        );

    @DgsData(parentType = "Query", field = "shows")
    public List<Show> shows(@InputArgument String titleFilter) {
        if(titleFilter != null) {
            return showData.stream().filter(s -> s.title().startsWith(titleFilter)).toList();
        } else {
            return showData;
        }
    }
}
