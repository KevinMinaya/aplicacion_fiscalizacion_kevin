package com.example.aplicativo_fiscalizacion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ActaPDFGenerator {

    public static File generarPDF(
            Context context,

            String expediente,
            String agente,
            String codigoOsinergmin,
            String registroHidrocarburos,
            String fecha,
            String horaApertura,
            String horaCierre,
            String direccion,
            String distrito,
            String provincia,
            String departamento,
            String rucDni,
            String telefono,

            String precioDiesel,
            String precioGasolinaRegular,
            String precioGasolinaPremium,
            String precioGasoholRegular,
            String precioGasoholPremium,

            String incumplimientos,
            String hechosVerificados,
            String otros
    ) throws IOException {

        PdfDocument documento = new PdfDocument();

        Paint titulo = new Paint();
        titulo.setColor(android.graphics.Color.BLACK);
        titulo.setTextSize(16);
        titulo.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        Paint texto = new Paint();
        texto.setColor(android.graphics.Color.BLACK);
        texto.setTextSize(9);

        Paint textoPequeno = new Paint();
        textoPequeno.setColor(android.graphics.Color.BLACK);
        textoPequeno.setTextSize(7);

        Paint linea = new Paint();
        linea.setColor(android.graphics.Color.BLACK);
        linea.setStyle(Paint.Style.STROKE);
        linea.setStrokeWidth(1);

        // ==========================
        // PÁGINA 1
        // ==========================

        PdfDocument.PageInfo infoPagina =
                new PdfDocument.PageInfo.Builder(595, 842, 1).create();

        PdfDocument.Page pagina = documento.startPage(infoPagina);
        Canvas canvas = pagina.getCanvas();

        float x = 35;
        float y = 35;

        // ENCABEZADO
        titulo.setTextSize(17);
        canvas.drawText("OSINERGMIN", x, y, titulo);

        textoPequeno.setTextSize(7);
        canvas.drawText("Oficina Regional Huánuco", x, y + 12, textoPequeno);
        canvas.drawText("Dirección: Pasaje Mayro N.º 121", x, y + 22, textoPequeno);
        canvas.drawText("Teléfono: 062 - 518499", x, y + 32, textoPequeno);

        canvas.drawRect(420, 25, 560, 55, linea);
        canvas.drawText("EXPEDIENTE Nro.", 430, 43, textoPequeno);
        canvas.drawText(expediente, 430, 52, textoPequeno);

        titulo.setTextSize(11);
        canvas.drawText(
                "ACTA DE FISCALIZACIÓN DEL CUMPLIMIENTO DEL PROCEDIMIENTO",
                120, 82, titulo
        );

        canvas.drawText(
                "DE ENTREGA DE INFORMACIÓN DE PRECIOS DE COMBUSTIBLES",
                145, 96, titulo
        );

        // DATOS DEL ESTABLECIMIENTO
        float top = 115;

        canvas.drawRect(x, top, 560, top + 150, linea);

        texto.setTextSize(8);

        canvas.drawText("AGENTE FISCALIZADO:", x + 5, top + 15, texto);
        canvas.drawText(agente, x + 120, top + 15, texto);

        canvas.drawText("CÓDIGO OSINERGMIN:", x + 5, top + 32, texto);
        canvas.drawText(codigoOsinergmin, x + 120, top + 32, texto);

        canvas.drawText("REGISTRO DE HIDROCARBUROS N.º:", x + 5, top + 49, texto);
        canvas.drawText(registroHidrocarburos, x + 180, top + 49, texto);

        canvas.drawText("FECHA DE DILIGENCIA:", x + 5, top + 66, texto);
        canvas.drawText(fecha, x + 120, top + 66, texto);

        canvas.drawText("HORA APERTURA:", x + 300, top + 66, texto);
        canvas.drawText(horaApertura, x + 390, top + 66, texto);

        canvas.drawText("HORA CIERRE:", x + 300, top + 83, texto);
        canvas.drawText(horaCierre, x + 390, top + 83, texto);

        canvas.drawText("DIRECCIÓN:", x + 5, top + 83, texto);
        canvas.drawText(direccion, x + 120, top + 83, texto);

        canvas.drawText("DISTRITO:", x + 5, top + 100, texto);
        canvas.drawText(distrito, x + 120, top + 100, texto);

        canvas.drawText("PROVINCIA:", x + 300, top + 100, texto);
        canvas.drawText(provincia, x + 390, top + 100, texto);

        canvas.drawText("DEPARTAMENTO:", x + 5, top + 117, texto);
        canvas.drawText(departamento, x + 120, top + 117, texto);

        canvas.drawText("RUC / DNI:", x + 300, top + 117, texto);
        canvas.drawText(rucDni, x + 390, top + 117, texto);

        canvas.drawText("TELÉFONO / FAX:", x + 5, top + 134, texto);
        canvas.drawText(telefono, x + 120, top + 134, texto);

        // INFORMACIÓN RECABADA
        y = top + 175;

        titulo.setTextSize(11);
        canvas.drawText("I. INFORMACIÓN RECABADA", x, y, titulo);

        float tablaTop = y + 10;
        float tablaBottom = tablaTop + 170;

        canvas.drawRect(x, tablaTop, 560, tablaBottom, linea);

        // Columnas
        canvas.drawLine(270, tablaTop, 270, tablaBottom, linea);
        canvas.drawLine(375, tablaTop, 375, tablaBottom, linea);
        canvas.drawLine(470, tablaTop, 470, tablaBottom, linea);

        texto.setTextSize(7);
        texto.setTypeface(Typeface.DEFAULT_BOLD);

        canvas.drawText("PRODUCTO FISCALIZADO", 45, tablaTop + 15, texto);
        canvas.drawText("PRECIO", 310, tablaTop + 15, texto);
        canvas.drawText("OBSERVACIÓN", 400, tablaTop + 15, texto);

        texto.setTypeface(Typeface.DEFAULT);

        String[][] precios = {
                {"Diesel B5 / B5 S50", precioDiesel},
                {"Gasolina Regular", precioGasolinaRegular},
                {"Gasolina Premium", precioGasolinaPremium},
                {"Gasohol Regular", precioGasoholRegular},
                {"Gasohol Premium", precioGasoholPremium}
        };

        float filaY = tablaTop + 38;

        for (String[] precio : precios) {

            canvas.drawLine(x, filaY + 8, 560, filaY + 8, linea);

            canvas.drawText(precio[0], 45, filaY, texto);
            canvas.drawText(precio[1], 310, filaY, texto);
            canvas.drawText("Registrado", 400, filaY, texto);

            filaY += 25;
        }

        // INCUMPLIMIENTOS
        y = tablaBottom + 25;

        titulo.setTextSize(11);
        canvas.drawText("II. HECHOS VERIFICADOS / INCUMPLIMIENTOS", x, y, titulo);

        canvas.drawRect(x, y + 10, 560, y + 145, linea);

        texto.setTextSize(8);

        canvas.drawText("INCUMPLIMIENTOS:", x + 8, y + 28, texto);
        escribirTexto(canvas, incumplimientos, x + 8, y + 43, 540, texto);

        canvas.drawText("HECHOS VERIFICADOS:", x + 8, y + 83, texto);
        escribirTexto(canvas, hechosVerificados, x + 8, y + 98, 540, texto);

        canvas.drawText(
                "Página 1 de 2",
                275, 825, textoPequeno
        );

        documento.finishPage(pagina);

        // ==========================
        // PÁGINA 2
        // ==========================

        infoPagina =
                new PdfDocument.PageInfo.Builder(595, 842, 2).create();

        pagina = documento.startPage(infoPagina);
        canvas = pagina.getCanvas();

        x = 35;
        y = 35;

        titulo.setTextSize(17);
        canvas.drawText("OSINERGMIN", x, y, titulo);

        textoPequeno.setTextSize(7);
        canvas.drawText(
                "Acta de Fiscalización del Cumplimiento del Procedimiento de Entrega de Información de Precios",
                x, y + 20, textoPequeno
        );

        canvas.drawText(
                "de Combustibles Derivados de Hidrocarburos PRICE",
                x, y + 32, textoPequeno
        );

        // INCUMPLIMIENTOS
        y = 85;

        titulo.setTextSize(11);
        canvas.drawText("INCUMPLIMIENTOS Y HECHOS VERIFICADOS", x, y, titulo);

        canvas.drawRect(x, y + 10, 560, y + 230, linea);

        texto.setTextSize(8);

        canvas.drawText("N.º", x + 8, y + 28, texto);
        canvas.drawText("INCUMPLIMIENTO", x + 45, y + 28, texto);
        canvas.drawText("HECHOS VERIFICADOS", x + 320, y + 28, texto);

        canvas.drawLine(x, y + 35, 560, y + 35, linea);
        canvas.drawLine(x + 35, y + 10, x + 35, y + 230, linea);
        canvas.drawLine(x + 305, y + 10, x + 305, y + 230, linea);

        canvas.drawText("1", x + 12, y + 55, texto);
        escribirTexto(
                canvas,
                incumplimientos,
                x + 43,
                y + 55,
                x + 295,
                texto
        );

        escribirTexto(
                canvas,
                hechosVerificados,
                x + 315,
                y + 55,
                550,
                texto
        );

        // OTRAS OCURRENCIAS
        y = 345;

        titulo.setTextSize(11);
        canvas.drawText("III. OTROS", x, y, titulo);

        canvas.drawRect(x, y + 10, 560, y + 100, linea);

        texto.setTextSize(8);

        canvas.drawText(
                "Otras ocurrencias detectadas en la fiscalización:",
                x + 8,
                y + 28,
                texto
        );

        escribirTexto(
                canvas,
                otros,
                x + 8,
                y + 45,
                550,
                texto
        );

        // DOCUMENTACIÓN
        y = 465;

        canvas.drawRect(x, y, 560, y + 60, linea);

        canvas.drawText(
                "Documentación recabada en la fiscalización:",
                x + 8,
                y + 18,
                texto
        );

        canvas.drawText(
                "Información registrada mediante el aplicativo de fiscalización.",
                x + 8,
                y + 35,
                texto
        );

        // MANIFESTACIONES
        y = 545;

        canvas.drawRect(x, y, 560, y + 60, linea);

        canvas.drawText(
                "Manifestaciones u observaciones del Agente Fiscalizado:",
                x + 8,
                y + 18,
                texto
        );

        canvas.drawText(
                otros,
                x + 8,
                y + 35,
                texto
        );

        // FIRMAS
        y = 680;

        canvas.drawLine(60, y, 240, y, linea);
        canvas.drawLine(350, y, 530, y, linea);

        canvas.drawText(
                "Firma del Fiscalizador de Osinergmin",
                80, y + 18, texto
        );

        canvas.drawText(
                "Firma de quien recibe",
                395, y + 18, texto
        );

        canvas.drawText(
                "Fiscalizador: ____________________",
                60, y + 45, texto
        );

        canvas.drawText(
                "Agente fiscalizado: _______________",
                350, y + 45, texto
        );

        canvas.drawText(
                "DNI: __________________",
                60, y + 62, texto
        );

        canvas.drawText(
                "DNI: __________________",
                350, y + 62, texto
        );

        // PIE
        textoPequeno.setTextSize(7);

        canvas.drawText(
                "Para trámites posteriores referentes a esta fiscalización, señalar el número de expediente.",
                x,
                795,
                textoPequeno
        );

        canvas.drawText(
                "Página 2 de 2",
                275,
                825,
                textoPequeno
        );

        documento.finishPage(pagina);

        // ==========================
        // GUARDAR PDF
        // ==========================

        File carpeta = new File(
                context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                "ActasFiscalizacion"
        );

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        String nombreArchivo = "Acta_PRICE_" + expediente.replaceAll("[^a-zA-Z0-9_-]", "_") + ".pdf";

        File archivo = new File(carpeta, nombreArchivo);

        FileOutputStream salida = new FileOutputStream(archivo);

        documento.writeTo(salida);
        salida.close();

        documento.close();

        return archivo;
    }

    private static void escribirTexto(
            Canvas canvas,
            String texto,
            float x,
            float y,
            float limiteX,
            Paint paint
    ) {

        if (texto == null || texto.isEmpty()) {
            return;
        }

        float anchoMaximo = limiteX - x;

        String[] palabras = texto.split(" ");
        StringBuilder linea = new StringBuilder();

        float posicionY = y;

        for (String palabra : palabras) {

            String prueba = linea.length() == 0
                    ? palabra
                    : linea + " " + palabra;

            if (paint.measureText(prueba) > anchoMaximo) {

                canvas.drawText(
                        linea.toString(),
                        x,
                        posicionY,
                        paint
                );

                posicionY += 12;
                linea = new StringBuilder(palabra);

            } else {

                linea = new StringBuilder(prueba);
            }
        }

        if (linea.length() > 0) {
            canvas.drawText(
                    linea.toString(),
                    x,
                    posicionY,
                    paint
            );
        }
    }
}