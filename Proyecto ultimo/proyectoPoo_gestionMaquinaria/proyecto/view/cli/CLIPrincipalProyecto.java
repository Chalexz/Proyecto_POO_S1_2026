package proyecto.view.cli;

import proyecto.model.SistemaGestion;
import model.Responsable;
import model.Maquinaria;
import validation.Validador;
import java.util.ArrayList;
import java.util.Scanner;

public class CLIPrincipalProyecto {

  private static final Scanner scanner = new Scanner(System.in);
  private static final SistemaGestion sistema = new SistemaGestion();
  private static final Validador validador = new Validador();

  public static void main(String[] args) {
    int opcion;
    do {
      mostrarMenu();
      opcion = leerOpcionMenu();
      procesarOpcion(opcion);
    } while (opcion != 0);
    System.out.println("\nHasta luego.");
    scanner.close();
  }

  // -------------------------------------------------------------------------
  // Menú principal
  // -------------------------------------------------------------------------

  private static void mostrarMenu() {
    System.out.println("\n========== GESTIÓN DE MAQUINARIA ==========");
    System.out.println(" 1.  Registrar responsable");
    System.out.println(" 2.  Consultar responsable por identificación");
    System.out.println(" 3.  Registrar equipo industrial");
    System.out.println(" 4.  Registrar herramienta industrial");
    System.out.println(" 5.  Asignar maquinaria a responsable");
    System.out.println(" 6.  Registrar mantenimiento a maquinaria");
    System.out.println(" 7.  Consultar información detallada de maquinaria");
    System.out.println(" 8.  Consultar maquinarias asignadas a un responsable");
    System.out.println(" 9.  Consultar tablas de depreciación de un responsable");
    System.out.println("10.  Consultar todas las maquinarias del sistema");
    System.out.println("11.  Listar responsables alfabéticamente");
    System.out.println("12.  Eliminar responsable");
    System.out.println(" 0.  Salir");
    System.out.println("============================================");
  }

  private static int leerOpcionMenu() {
    while (true) {
      System.out.print("Seleccione una opción: ");
      String entrada = scanner.nextLine().trim();
      try {
        return Integer.parseInt(entrada);
      } catch (NumberFormatException e) {
        System.out.println("Error: ingrese un número entero.");
      }
    }
  }

  private static void procesarOpcion(int opcion) {
    switch (opcion) {
      case 1:  registrarResponsable();               break;
      case 2:  consultarResponsable();               break;
      case 3:  registrarEquipoIndustrial();          break;
      case 4:  registrarHerramientaIndustrial();     break;
      case 5:  asignarMaquinariaAResponsable();      break;
      case 6:  registrarMantenimiento();             break;
      case 7:  consultarMaquinaria();                break;
      case 8:  consultarMaquinariasDeResponsable();  break;
      case 9:  consultarDepreciacionDeResponsable(); break;
      case 10: consultarTodasMaquinarias();          break;
      case 11: listarResponsablesAlfabeticamente();  break;
      case 12: eliminarResponsable();                break;
      case 0:                                        break;
      default: System.out.println("Opción no válida.");
    }
  }

  // -------------------------------------------------------------------------
  // Funcionalidades
  // -------------------------------------------------------------------------

  private static void registrarResponsable() {
    System.out.println("\n--- Registro de responsable ---");

    String identificacion = leerStringValido("Número de identificación: ");
    if (sistema.existeResponsable(identificacion)) {
      System.out.println("Error: ya existe un responsable con esa identificación.");
      return;
    }

    String nombre = leerStringValido("Nombre completo: ");
    String fechaNacimiento = leerFechaValida("Fecha de nacimiento (dd/MM/yyyy): ");

    String codigo = sistema.registrarResponsable(identificacion, nombre, fechaNacimiento);
    System.out.println("\nResponsable registrado exitosamente.");
    System.out.println("Se ha asignado el código: " + codigo);
  }

  private static void consultarResponsable() {
    System.out.println("\n--- Consulta de responsable ---");
    String identificacion = leerStringValido("Número de identificación: ");

    Responsable responsable = sistema.buscarResponsable(identificacion);
    if (responsable == null) {
      System.out.println("No existe un responsable registrado con esa identificación.");
      return;
    }
    imprimirDatosResponsable(responsable);
  }

  private static void registrarEquipoIndustrial() {
    System.out.println("\n--- Registro de equipo industrial ---");

    double valor = leerDoublePositivo("Valor: ");
    int vidaUtil = leerIntPositivo("Vida útil (años): ");
    String marca = leerStringValido("Marca: ");
    String descripcion = leerStringValido("Descripción: ");
    double peso = leerDoublePositivo("Peso (kg): ");
    double volumen = leerDoublePositivo("Volumen (m³): ");

    String codigo = sistema.registrarEquipoIndustrial(valor, vidaUtil, marca, descripcion, peso, volumen);
    Maquinaria equipo = sistema.buscarMaquinaria(codigo);

    System.out.println("\nEquipo industrial registrado exitosamente.");
    imprimirDatosMaquinaria(equipo);
    System.out.println("\n--- Tabla de depreciación ---");
    imprimirTablaDepreciacion(sistema.generarTablaDepreciacion(codigo));
  }

  private static void registrarHerramientaIndustrial() {
    System.out.println("\n--- Registro de herramienta industrial ---");

    double valor = leerDoublePositivo("Valor: ");
    int vidaUtil = leerIntPositivo("Vida útil (años): ");
    String marca = leerStringValido("Marca: ");
    String descripcion = leerStringValido("Descripción: ");

    double valorSalvamento;
    while (true) {
      valorSalvamento = leerDoubleNoNegativo("Valor de salvamento: ");
      if (!validador.esMenorQueEstricto(valorSalvamento, valor)) {
        System.out.println("Error: el valor de salvamento debe ser menor que el valor del activo (" + valor + ").");
      } else {
        break;
      }
    }

    double tasa;
    while (true) {
      tasa = leerDouble("Tasa de depreciación (%): ");
      if (!validador.esDoubleEnRango(tasa, 0.001, 100)) {
        System.out.println("Error: la tasa debe ser mayor que 0% y menor o igual que 100%.");
      } else {
        break;
      }
    }

    String codigo = sistema.registrarHerramientaIndustrial(valor, vidaUtil, marca, descripcion, valorSalvamento, tasa);
    Maquinaria herramienta = sistema.buscarMaquinaria(codigo);

    System.out.println("\nHerramienta industrial registrada exitosamente.");
    imprimirDatosMaquinaria(herramienta);
    System.out.println("\n--- Tabla de depreciación ---");
    imprimirTablaDepreciacion(sistema.generarTablaDepreciacion(codigo));
  }

  private static void asignarMaquinariaAResponsable() {
    System.out.println("\n--- Asignación de maquinaria a responsable ---");

    String identificacion = leerStringValido("Número de identificación del responsable: ");
    Responsable responsable = sistema.buscarResponsable(identificacion);
    if (responsable == null) {
      System.out.println("Error: no existe un responsable con esa identificación.");
      return;
    }
    imprimirDatosResponsable(responsable);

    String codigoMaquinaria = leerStringValido("Código de la maquinaria a asignar: ");
    Maquinaria maquinaria = sistema.buscarMaquinaria(codigoMaquinaria);
    if (maquinaria == null) {
      System.out.println("Error: no existe una maquinaria con ese código.");
      return;
    }

    if (maquinaria.tieneResponsable()) {
      System.out.println("Error: la maquinaria " + codigoMaquinaria
          + " ya se encuentra bajo la responsabilidad de otra persona.");
      return;
    }

    sistema.asignarMaquinaria(identificacion, codigoMaquinaria);

    System.out.println("\nLa maquinaria fue asignada exitosamente.");
    System.out.println("Responsable: " + responsable.getNombreCompleto());
    System.out.println("Código del responsable: " + responsable.getCodigo());
    System.out.println("Identificación: " + responsable.getIdentificacion());
    System.out.println("Maquinaria asignada: " + codigoMaquinaria);
    System.out.println("Tipo: " + maquinaria.getTipo());
    System.out.println("Marca: " + maquinaria.getMarca());
    System.out.println("Descripción: " + maquinaria.getDescripcion());
  }

  private static void registrarMantenimiento() {
    System.out.println("\n--- Registro de mantenimiento ---");

    String codigoMaquinaria = leerStringValido("Código de la maquinaria: ");
    if (sistema.buscarMaquinaria(codigoMaquinaria) == null) {
      System.out.println("Error: no existe una maquinaria con ese código.");
      return;
    }

    double costo = leerDoubleNoNegativo("Costo del mantenimiento: ");
    String fecha = leerFechaValida("Fecha del mantenimiento (dd/MM/yyyy): ");
    String descripcion = leerStringValido("Descripción del mantenimiento: ");

    sistema.registrarMantenimiento(codigoMaquinaria, costo, fecha, descripcion);

    Maquinaria maquinaria = sistema.buscarMaquinaria(codigoMaquinaria);
    System.out.println("\nMantenimiento registrado exitosamente.");
    System.out.println("Código de maquinaria: " + codigoMaquinaria);
    System.out.println("Marca: " + maquinaria.getMarca());
    System.out.println("Descripción: " + maquinaria.getDescripcion());
    System.out.println("Costo del mantenimiento: " + costo);
    System.out.println("Fecha del mantenimiento: " + fecha);
    System.out.println("Descripción del mantenimiento: " + descripcion);
  }

  private static void consultarMaquinaria() {
    System.out.println("\n--- Información detallada de maquinaria ---");
    String codigo = leerStringValido("Código de la maquinaria: ");

    Maquinaria maquinaria = sistema.buscarMaquinaria(codigo);
    if (maquinaria == null) {
      System.out.println("No existe una maquinaria con ese código.");
      return;
    }

    imprimirDatosMaquinaria(maquinaria);

    String codigoResponsable = maquinaria.getCodigoResponsable();
    System.out.println("Responsable asignado: "
        + (codigoResponsable != null ? codigoResponsable : "Sin responsable asignado."));

    ArrayList<String> mantenimientos = maquinaria.getMantenimientosFormateados();
    if (mantenimientos.isEmpty()) {
      System.out.println("\nNo hay mantenimientos registrados para esta maquinaria.");
    } else {
      System.out.println("\n--- Mantenimientos registrados ---");
      for (int i = 0; i < mantenimientos.size(); i++) {
        System.out.println((i + 1) + ". " + mantenimientos.get(i));
      }
    }

    System.out.println("\n--- Tabla de depreciación ---");
    imprimirTablaDepreciacion(sistema.generarTablaDepreciacion(codigo));
  }

  private static void consultarMaquinariasDeResponsable() {
    System.out.println("\n--- Maquinarias asignadas a responsable ---");
    String identificacion = leerStringValido("Número de identificación del responsable: ");

    Responsable responsable = sistema.buscarResponsable(identificacion);
    if (responsable == null) {
      System.out.println("No existe un responsable con esa identificación.");
      return;
    }

    imprimirDatosResponsable(responsable);

    ArrayList<Maquinaria> maquinarias = sistema.getMaquinariasDeResponsable(identificacion);
    if (maquinarias.isEmpty()) {
      System.out.println("Este responsable no tiene maquinarias asignadas.");
      return;
    }

    System.out.println("\n--- Maquinarias bajo su responsabilidad ---");
    for (Maquinaria m : maquinarias) {
      imprimirDatosMaquinaria(m);

      ArrayList<String> mantenimientos = m.getMantenimientosFormateados();
      if (mantenimientos.isEmpty()) {
        System.out.println("  Sin mantenimientos registrados.");
      } else {
        System.out.println("  Mantenimientos:");
        for (int i = 0; i < mantenimientos.size(); i++) {
          System.out.println("  " + (i + 1) + ". " + mantenimientos.get(i));
        }
      }

      System.out.println("  --- Tabla de depreciación ---");
      imprimirTablaDepreciacion(sistema.generarTablaDepreciacion(m.getCodigo()));
      System.out.println();
    }
  }

  private static void consultarDepreciacionDeResponsable() {
    System.out.println("\n--- Tablas de depreciación de maquinarias de responsable ---");
    String identificacion = leerStringValido("Número de identificación del responsable: ");

    Responsable responsable = sistema.buscarResponsable(identificacion);
    if (responsable == null) {
      System.out.println("No existe un responsable con esa identificación.");
      return;
    }

    ArrayList<Maquinaria> maquinarias = sistema.getMaquinariasDeResponsable(identificacion);
    if (maquinarias.isEmpty()) {
      System.out.println("Este responsable no tiene maquinarias asignadas.");
      return;
    }

    for (Maquinaria m : maquinarias) {
      System.out.println("\nMaquinaria: " + m.getCodigo() + " - " + m.getMarca());
      imprimirTablaDepreciacion(sistema.generarTablaDepreciacion(m.getCodigo()));
    }
  }

  private static void consultarTodasMaquinarias() {
    System.out.println("\n--- Todas las maquinarias registradas (orden ascendente por vida útil) ---");

    ArrayList<Maquinaria> maquinarias = sistema.getAllMaquinariasOrdenadas();
    if (maquinarias.isEmpty()) {
      System.out.println("No hay maquinarias registradas.");
      return;
    }

    for (Maquinaria m : maquinarias) {
      System.out.println("\nCódigo: " + m.getCodigo());
      System.out.println("Tipo: " + m.getTipo());
      System.out.println("Marca: " + m.getMarca());
      System.out.println("Descripción: " + m.getDescripcion());
      System.out.println("Valor: " + m.getValor());
      System.out.println("Vida útil: " + m.getVidaUtil() + " años");
      String resp = m.getCodigoResponsable();
      System.out.println("Responsable: " + (resp != null ? resp : "Sin responsable asignado."));
    }
  }

  private static void listarResponsablesAlfabeticamente() {
    System.out.println("\n--- Responsables registrados (orden alfabético) ---");

    ArrayList<Responsable> responsables = sistema.getAllResponsablesOrdenados();
    if (responsables.isEmpty()) {
      System.out.println("No hay responsables registrados.");
      return;
    }

    for (Responsable r : responsables) {
      System.out.println("\nCódigo: " + r.getCodigo());
      System.out.println("Identificación: " + r.getIdentificacion());
      System.out.println("Nombre completo: " + r.getNombreCompleto());
      System.out.println("Fecha de nacimiento: " + r.getFechaNacimiento());
      System.out.println("Cantidad de maquinarias: " + r.getCantidadMaquinarias());
    }
  }

  private static void eliminarResponsable() {
    System.out.println("\n--- Eliminar responsable ---");
    String identificacion = leerStringValido("Número de identificación: ");

    Responsable responsable = sistema.buscarResponsable(identificacion);
    if (responsable == null) {
      System.out.println("No existe un responsable con esa identificación.");
      return;
    }

    imprimirDatosResponsable(responsable);

    System.out.print("¿Desea eliminar a este responsable? (S/N): ");
    String confirmacion = scanner.nextLine().trim().toUpperCase();
    if (!confirmacion.equals("S")) {
      System.out.println("Operación cancelada.");
      return;
    }

    sistema.eliminarResponsable(identificacion);
    System.out.println("El responsable fue eliminado exitosamente.");
    System.out.println("Sus maquinarias permanecen registradas en el sistema sin responsable asignado.");
  }

  // -------------------------------------------------------------------------
  // Métodos de impresión
  // -------------------------------------------------------------------------

  private static void imprimirDatosResponsable(Responsable r) {
    System.out.println("\n--- Información del responsable ---");
    System.out.println("Código: " + r.getCodigo());
    System.out.println("Identificación: " + r.getIdentificacion());
    System.out.println("Nombre completo: " + r.getNombreCompleto());
    System.out.println("Fecha de nacimiento: " + r.getFechaNacimiento());
  }

  private static void imprimirDatosMaquinaria(Maquinaria m) {
    System.out.println("\nCódigo: " + m.getCodigo());
    System.out.println("Tipo: " + m.getTipo());
    System.out.println("Marca: " + m.getMarca());
    System.out.println("Descripción: " + m.getDescripcion());
    System.out.println("Valor: " + m.getValor());
    System.out.println("Vida útil: " + m.getVidaUtil() + " años");
    System.out.println(m.getDatosEspecificos());
  }

  // Cada fila: double[] {año, valorInicial, depAnual, depAcumulada, valorFinal}
  private static void imprimirTablaDepreciacion(ArrayList<double[]> tabla) {
    if (tabla == null || tabla.isEmpty()) {
      System.out.println("No se pudo generar la tabla de depreciación.");
      return;
    }
    System.out.printf("%-6s %-16s %-20s %-24s %-14s%n",
        "Año", "Valor inicial", "Depreciación anual", "Depreciación acumulada", "Valor final");
    for (double[] fila : tabla) {
      System.out.printf("%-6.0f %-16.2f %-20.2f %-24.2f %-14.2f%n",
          fila[0], fila[1], fila[2], fila[3], fila[4]);
    }
  }

  // -------------------------------------------------------------------------
  // Métodos de lectura con validación integrada
  // -------------------------------------------------------------------------

  private static String leerStringValido(String mensaje) {
    while (true) {
      System.out.print(mensaje);
      String entrada = scanner.nextLine();
      if (validador.esStringValido(entrada)) {
        return entrada.trim();
      }
      System.out.println("Error: el campo no puede estar vacío.");
    }
  }

  private static double leerDouble(String mensaje) {
    while (true) {
      System.out.print(mensaje);
      String entrada = scanner.nextLine().trim();
      try {
        return Double.parseDouble(entrada);
      } catch (NumberFormatException e) {
        System.out.println("Error: ingrese un valor numérico válido.");
      }
    }
  }

  private static double leerDoublePositivo(String mensaje) {
    while (true) {
      double valor = leerDouble(mensaje);
      if (validador.esDoublePositivo(valor)) {
        return valor;
      }
      System.out.println("Error: el valor debe ser mayor que cero.");
    }
  }

  private static double leerDoubleNoNegativo(String mensaje) {
    while (true) {
      double valor = leerDouble(mensaje);
      if (validador.esDoubleNoNegativo(valor)) {
        return valor;
      }
      System.out.println("Error: el valor no puede ser negativo.");
    }
  }

  private static int leerIntPositivo(String mensaje) {
    while (true) {
      System.out.print(mensaje);
      String entrada = scanner.nextLine().trim();
      try {
        int valor = Integer.parseInt(entrada);
        if (validador.esIntPositivo(valor)) {
          return valor;
        }
        System.out.println("Error: el valor debe ser un entero mayor que cero.");
      } catch (NumberFormatException e) {
        System.out.println("Error: ingrese un número entero válido.");
      }
    }
  }

  private static String leerFechaValida(String mensaje) {
    while (true) {
      System.out.print(mensaje);
      String entrada = scanner.nextLine().trim();
      if (validador.esFechaValida(entrada)) {
        return entrada;
      }
      System.out.println("Error: fecha inválida. Use el formato dd/MM/yyyy (ej: 15/06/2026).");
    }
  }
}
