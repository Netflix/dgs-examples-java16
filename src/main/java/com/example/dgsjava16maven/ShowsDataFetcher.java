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
