package com.enigmacamp.enigmacoop.controller;


import com.enigmacamp.enigmacoop.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    private List<Map<String,Object>> users = List.of(
            Map.of(
                    "id",1,
                    "Name","Budi"
            ),
            Map.of(
                    "id",2,
                    "Name","Rendi"
            )
    );

    private List<String> fruits = List.of("Apel Rusia","Apel Arab","Mangga","Alpukat","Anggur","Anggur Muda","Mangga muda");

    @DeleteMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/users")
    public List<Map<String,Object>> getListUsers(){
        return users;
    }

    @GetMapping("/users/{urName}") // localhost:8080/users/rendi -> Hi, rendi
    public String getUserById(@PathVariable String urName){
        return "Hi, " + urName;
    }

    @GetMapping("/fruits") // localhost:8080/fruits?search=mangga&size=5
    public List<String> getFruits(
            @RequestParam String search,
            @RequestParam String size
            ){
        return fruits
                .stream()
                .filter(fruit -> fruit.toLowerCase()
                        .contains(search.toLowerCase()))
                .toList();
    }

//    localhost:8080/users?age=10&lastName=rio // filter data


    @PostMapping("/users")
    public Map<Object, Object> createUser(@RequestBody User user){
        return Map.of(
                        "Message","Success Create New User",
                        "Data",user
        );
    };

    @GetMapping("/{id}")
    public void deleteFruits(@PathVariable String id){
        System.out.println("Data Terhapus");
    }

    @PutMapping
    public void updateUser(){

    }

//    @PatchMapping
    /**
     * Kunjungi link dibawah ini untuk mendapatkan Hadiahnya
     * dorpriserandom (domanservice/asd98fadsf) -> GETMAPPING > Delete Mapping
     */


    /**
     * Branching Handson SpringBoot enigma- | Incubation#21 Java
     * Master -> introduction
     * 01-JPA -> setup entity, repository, migration
     * 02-ResponEntity -> Data type for response JSON more simplify,
     * 03-Pagination -> limited data
     * 04-DTO -> Data Transfer Object
     * 05-Transaction -> include Relation between table
     * 06-Validation
     * 07-Specification -> filter with ORM,instead native query
     * 08-Security -> Implementation Authentication and Autorization with JWT
     * 09-Rest Client -> Consume API External, Ex : payment gateway with midtrans
     * 10-Swagger -> Documentation API
     */
}





