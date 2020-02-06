package utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import entity.common.CommonEntity;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class ExcelUtils {
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + "." + ExcelTypeEnum.XLSX.getValue(), "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportExcel(List<? extends CommonEntity> systems, Class pojoClass, String fileName, String sheetName, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(fileName, sheetName, ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, systems);
        downLoadExcel(fileName, response, workbook);
    }
}

enum ExcelTypeEnum {
    XLS("xls"), XLSX("xlsx");
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    ExcelTypeEnum(String value) {
        this.value = value;
    }
}