package pojo;

import lombok.*;

//TODO Country POJO class that have same encapsulated field as Countries table
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Countries {

    private String country_id;
    private String country_name;
    private int region_id;

}
