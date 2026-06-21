package validation;

public class Validador {

  public boolean esStringValido(String texto) {
    return texto != null && !texto.trim().isEmpty();
  }

  public boolean esDoublePositivo(double valor) {
    return valor > 0;
  }

  public boolean esIntPositivo(int valor) {
    return valor > 0;
  }

  public boolean esDoubleNoNegativo(double valor) {
    return valor >= 0;
  }

  public boolean esDoubleEnRango(double valor, double minIncluyente, double maxIncluyente) {
    return valor >= minIncluyente && valor <= maxIncluyente;
  }

  public boolean esMenorQueEstricto(double valor, double limite) {
    return valor < limite;
  }

  // Formato esperado: dd/MM/yyyy
  public boolean esFechaValida(String fecha) {
    if (!esStringValido(fecha)) {
      return false;
    }
    String[] partes = fecha.trim().split("/");
    if (partes.length != 3) {
      return false;
    }
    try {
      int dia = Integer.parseInt(partes[0].trim());
      int mes = Integer.parseInt(partes[1].trim());
      int anio = Integer.parseInt(partes[2].trim());
      if (mes < 1 || mes > 12 || anio < 1900 || anio > 2100) {
        return false;
      }
      return dia >= 1 && dia <= diasEnMes(mes, anio);
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private int diasEnMes(int mes, int anio) {
    int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    if (mes == 2 && esBisiesto(anio)) {
      return 29;
    }
    return diasPorMes[mes - 1];
  }

  private boolean esBisiesto(int anio) {
    return (anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0;
  }
}
