package com.enagas.controller;

import com.enagas.model.*;
import com.enagas.service.EnagasService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/enagas")
public class EnagasController {

    private final EnagasService enagasService;

    public EnagasController(EnagasService enagasService) {
        this.enagasService = enagasService;
    }

    @PostMapping("/volumen")
    @Operation(summary = "Obtain volume of emissions", description = "Calc the volume of emissions divide the emissions of factorEmision")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getVolume(@RequestBody EmisionesDTO emisionesDTO) {
        return enagasService.calcVolume(emisionesDTO);
    }

    @PostMapping("/energia")
    @Operation(summary = "Obtain energy of emissions", description = "Calc the energy of emissions multiply the volume for the PCS")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getEnergy(@RequestBody EmisionesDTO emisionesDTO) {
        return enagasService.calcEnergy(emisionesDTO);
    }

    @PostMapping("/leerExcel")
    @Operation(summary = "Obtain list of info from excel", description = "Extract data from excel sent whit exact format in this")
    @ResponseStatus(HttpStatus.OK)
    public List<ExcelDTO> listarExecel(@RequestParam MultipartFile archivo) throws IOException, InterruptedException {
        return enagasService.leerExcel(archivo);
    }

    @PostMapping("/login")
    @Operation
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody UserDTO userDTO) {
        return enagasService.login(userDTO);
    }

    @PostMapping("/validar")
    @Operation(summary = "Validate token JWT", description = "Validate token for use this to acces to the other endpoints")
    @ResponseStatus(HttpStatus.OK)
    public String validar(@RequestBody String token) {
        enagasService.ValidadorToken(token);
        return "token valido";
    }

    @PostMapping("/new")
    @Operation(summary = "Create a new User", description = "This endpoint is used to create a new users")
    @ResponseStatus(HttpStatus.OK)
    public String newUser(@RequestBody UserDTO userDTO) {
        return enagasService.CreateUser(userDTO);
    }
}
