package com.enagas.service;

import com.enagas.config.JWTconfig;
import com.enagas.model.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.enagas.config.JWTconfig.generarToken;
import static com.enagas.config.JWTconfig.validarToken;

@Service
public class EnagasService {

    @Autowired
    private UserRepository userRepository;

    public BigDecimal calcVolume(EmisionesDTO emisionesDTO) {
        return emisionesDTO.getEmisiones().divide(emisionesDTO.getFactorEmision(), 33, RoundingMode.HALF_UP);
    }

    public BigDecimal calcEnergy(EmisionesDTO emisionesDTO) {
        BigDecimal Volume = calcVolume(emisionesDTO);
        return emisionesDTO.getPCS().multiply(Volume);
    }

    public List<ExcelDTO> leerExcel(MultipartFile archivo) throws IOException, InterruptedException {
        List<ExcelDTO> lista = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(archivo.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        if (rowIterator.hasNext()) {
            rowIterator.next();  // Saltar encabezado
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (filaVacia(row)) {
                break;
            }

            ExcelDTO dto = new ExcelDTO();

            dto.setFecha_movimiento(obtenerValorString(row.getCell(0)));
            dto.setInfraestructura(obtenerValorString(row.getCell(1)));
            dto.setObservaciones(obtenerValorString(row.getCell(2)));
            dto.setDescripcion(obtenerValorString(row.getCell(3)));
            dto.setValor(obtenerValorNumerico(row.getCell(4)));
            dto.setJustificante(obtenerValorString(row.getCell(5)));
            dto.setObservacionesAEAT(obtenerValorString(row.getCell(6)));

            lista.add(dto);

        }
        Thread.sleep(3000);
        return lista;
    }

    public String CreateToken(UserDTO userDTO) {
        System.out.println("JWT: " + generarToken(userDTO));
        return generarToken(userDTO);
    }

    public void ValidadorToken(String token) {
        validarToken(token);
    }

    public String login(UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findByName(userDTO.getName());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(userDTO.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        UserDTO userInfo = new UserDTO();
        userInfo.setName(user.getName());
        userInfo.setRole(user.getRole());

        return JWTconfig.generarToken(userInfo);
    }

    public String CreateUser(UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findByName(userDTO.getName());
        if (existingUser.isPresent()) {
           return ("Ya existe un usuario con ese nombre");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        userRepository.save(user);
        return "User created";
    }

    private String obtenerValorString(Cell cell) {
        if (cell != null && cell.getCellTypeEnum() == CellType.STRING) {
            return cell.getStringCellValue();
        }
        return null;
    }

    private BigDecimal obtenerValorNumerico(Cell cell) {
        if (cell != null && cell.getCellTypeEnum() == CellType.NUMERIC) {
            return BigDecimal.valueOf(cell.getNumericCellValue());
        }
        return null;
    }

    private boolean filaVacia(Row row) {
        for (int i = 0; i <= 6; i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellTypeEnum() != CellType.BLANK) {
                if (cell.getCellTypeEnum() == CellType.STRING && !cell.getStringCellValue().trim().isEmpty()) {
                    return false;
                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                    return false;
                }
            }
        }
        return true;
    }

}
