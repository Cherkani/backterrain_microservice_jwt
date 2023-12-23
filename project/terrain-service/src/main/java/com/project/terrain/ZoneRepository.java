package com.project.terrain;

import com.project.terrain.ralations.Zone;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient( name = "ZONEVILLE-SERVICE")
public  interface ZoneRepository  {

        @GetMapping("/zone/{id}")
        Zone zoneById(@PathVariable Long id) ;

}
