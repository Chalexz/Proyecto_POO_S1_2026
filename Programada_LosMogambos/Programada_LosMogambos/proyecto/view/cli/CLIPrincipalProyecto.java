package proyecto.view.cli;

import proyecto.model.Sistema;
import proyecto.model.Responsable;
import proyecto.model.Maquinaria;
import proyecto.model.Mantenimiento;
import proyecto.model.EquipoIndustrial;
import proyecto.model.HerramientaIndustrial;
import proyecto.util.Validador;
import java.util.ArrayList;
import java.util.Scanner;

public class CLIPrincipalProyecto {

  private static final Scanner scanner = new Scanner(System.in);
  private static final Sistema sistema = new Sistema();
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
    if (sistema.buscarResponsablePorIdentificacion(identificacion) != null) {
      System.out.println("Error: ya existe un responsable con esa identificación.");
      return;
    }

    String nombre = leerStringValido("Nombre completo: ");
    String fechaNacimiento = leerFechaValida("Fecha de nacimiento (dd/MM/yyyy): ");

    String codigo = sistema.registrarResponsable(identificacion, fechaNacimiento, nombre);
    System.out.println("\nResponsable registrado exitosamente.");
    System.out.println("Se ha asignado el código: " + codigo);
  }

  private static void consultarResponsable() {
    System.out.println("\n--- Consulta de responsable ---");
    String identificacion = leerStringValido("Número de identificación: ");

    Responsable responsable = sistema.buscarResponsablePorIdentificacion(identificacion);
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
    Maquinaria equipo = sistema.buscarMaquinariaPorCodigo(codigo);

    System.out.println("\nEquipo industrial registrado exitosamente.");
    imprimirDatosMaquinaria(equipo);
    System.out.println("\n--- Tabla de depreciación ---");
    imprimirTablaDepreciacion(equipo.calcularTablaDepreciacion());
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
    Maquinaria herramienta = sistema.buscarMaquinariaPorCodigo(codigo);

    System.out.println("\nHerramienta industrial registrada exitosamente.");
    imprimirDatosMaquinaria(herramienta);
    System.out.println("\n--- Tabla de depreciación ---");
    imprimirTablaDepreciacion(herramienta.calcularTablaDepreciacion());
  }

  private static void asignarMaquinariaAResponsable() {
    System.out.println("\n--- Asignación de maquinaria a responsable ---");

    String identificacion = leerStringValido("Número de identificación del responsable: ");
    Responsable responsable = sistema.buscarResponsablePorIdentificacion(identificacion);
    if (responsable == null) {
      System.out.println("Error: no existe un responsable con esa identificación.");
      return;
    }
    imprimirDatosResponsable(responsable);

    String codigoMaquinaria = leerStringValido("Código de la maquinaria a asignar: ");
    Maquinaria maquinaria = sistema.buscarMaquinariaPorCodigo(codigoMaquinaria);
    if (maquinaria == null) {
      System.out.println("Error: no existe una maquinaria con ese código.");
      return;
    }

    if (maquinaria.getResponsable() != null) {
      System.out.println("Error: la maquinaria " + codigoMaquinaria
          + " ya se encuentra bajo la responsabilidad de otra persona.");
      return;
    }

    sistema.asignarMaquinariaAResponsable(identificacion, codigoMaquinaria);

    System.out.println("\nLa maquinaria fue asignada exitosamente.");
    System.out.println("Responsable: " + responsable.getNombreCompleto());
    System.out.println("Código del responsable: " + responsable.getCodigo());
    System.out.println("Identificación: " + responsable.getIdentificacion());
    System.out.println("Maquinaria asignada: " + codigoMaquinaria);
    System.out.println("Tipo: " + obtenerTipo(maquinaria));
    System.out.println("Marca: " + maquinaria.getMarca());
    System.out.println("Descripción: " + maquinaria.getDescripcion());
  }

  private static void registrarMantenimiento() {
    System.out.println("\n--- Registro de mantenimiento ---");

    String codigoMaquinaria = leerStringValido("Código de la maquinaria: ");
    if (sistema.buscarMaquinariaPorCodigo(codigoMaquinaria) == null) {
      System.out.println("Error: no existe una maquinaria con ese código.");
      return;
    }

    double costo = leerDoubleNoNegativo("Costo del mantenimiento: ");
    String fecha = leerFechaValida("Fecha del mantenimiento (dd/MM/yyyy): ");
    String descripcion = leerStringValido("Descripción del mantenimiento: ");

    sistema.registrarMantenimiento(codigoMaquinaria, costo, fecha, descripcion);

    Maquinaria maquinaria = sistema.buscarMaquinariaPorCodigo(codigoMaquinaria);
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

    Maquinaria maquinaria = sistema.buscarMaquinariaPorCodigo(codigo);
    if (maquinaria == null) {
      System.out.println("No existe una maquinaria con ese código.");
      return;
    }

    imprimirDatosMaquinaria(maquinaria);

    Responsable resp = maquinaria.getResponsable();
    System.out.println("Responsable asignado: "
        + (resp != null ? resp.getCodigo() : "Sin responsable asignado."));

    ArrayList<Mantenimiento> mantenimientos = maquinaria.getMantenimientos();
    if (mantenimientos.isEmpty()) {
      System.out.println("\nNo hay mantenimientos registrados para esta maquinaria.");
    } else {
      System.out.println("\n--- Mantenimientos registrados ---");
      for (int i = 0; i < mantenimientos.size(); i++) {
        System.out.println((i + 1) + ". " + mantenimientos.get(i));
      }
    }

    System.out.println("\n--- Tabla de depreciación ---");
    imprimirTablaDepreciacion(maquinaria.calcularTablaDepreciacion());
  }

  private static void consultarMaquinariasDeResponsable() {
    System.out.println("\n--- Maquinarias asignadas a responsable ---");
    String identificacion = leerStringValido("Número de identificación del responsable: ");

    Responsable responsable = sistema.buscarResponsablePorIdentificacion(identificacion);
    if (responsable == null) {
      System.out.println("No existe un responsable con esa identificación.");
      return;
    }

    imprimirDatosResponsable(responsable);

    ArrayList<Maquinaria> maquinarias = responsable.getMaquinarias();
    if (maquinarias.isEmpty()) {
      System.out.println("Este responsable no tiene maquinarias asignadas.");
      return;
    }

    System.out.println("\n--- Maquinarias bajo su responsabilidad ---");
    for (Maquinaria m : maquinarias) {
      imprimirDatosMaquinaria(m);

      ArrayList<Mantenimiento> mantenimientos = m.getMantenimientos();
      if (mantenimientos.isEmpty()) {
        System.out.println("  Sin mantenimientos registrados.");
      } else {
        System.out.println("  Mantenimientos:");
        for (int i = 0; i < mantenimientos.size(); i++) {
          System.out.println("  " + (i + 1) + ". " + mantenimientos.get(i));
        }
      }

      System.out.println("  --- Tabla de depreciación ---");
      imprimirTablaDepreciacion(m.calcularTablaDepreciacion());
      System.out.println();
    }
  }

  private static void consultarDepreciacionDeResponsable() {
    System.out.println("\n--- Tablas de depreciación de maquinarias de responsable ---");
    String identificacion = leerStringValido("Número de identificación del responsable: ");

    Responsable responsable = sistema.buscarResponsablePorIdentificacion(identificacion);
    if (responsable == null) {
      System.out.println("No existe un responsable con esa identificación.");
      return;
    }

    ArrayList<Maquinaria> maquinarias = responsable.getMaquinarias();
    if (maquinarias.isEmpty()) {
      System.out.println("Este responsable no tiene maquinarias asignadas.");
      return;
    }

    for (Maquinaria m : maquinarias) {
      System.out.println("\nMaquinaria: " + m.getCodigo() + " - " + m.getMarca());
      imprimirTablaDepreciacion(m.calcularTablaDepreciacion());
    }
  }

  private static void consultarTodasMaquinarias() {
    System.out.println("\n--- Todas las maquinarias registradas (orden ascendente por vida útil) ---");

    ArrayList<Maquinaria> maquinarias = sistema.listarMaquinariasPorVidaUtil();
    if (maquinarias.isEmpty()) {
      System.out.println("No hay maquinarias registradas.");
      return;
    }

    for (Maquinaria m : maquinarias) {
      System.out.println("\nCódigo: " + m.getCodigo());
      System.out.println("Tipo: " + obtenerTipo(m));
      System.out.println("Marca: " + m.getMarca());
      System.out.println("Descripción: " + m.getDescripcion());
      System.out.println("Valor: " + m.getValor());
      System.out.println("Vida útil: " + m.getVidaUtil() + " años");
      Responsable resp = m.getResponsable();
      System.out.println("Responsable: " + (resp != null ? resp.getCodigo() : "Sin responsable asignado."));
    }
  }

  private static void listarResponsablesAlfabeticamente() {
    System.out.println("\n--- Responsables registrados (orden alfabético) ---");

    ArrayList<Responsable> responsables = sistema.listarResponsablesAlfabeticamente();
    if (responsables.isEmpty()) {
      System.out.println("No hay responsables registrados.");
      return;
    }

    for (Responsable r : responsables) {
      System.out.println("\nCódigo: " + r.getCodigo());
      System.out.println("Identificación: " + r.getIdentificacion());
      System.out.println("Nombre completo: " + r.getNombreCompleto());
      System.out.println("Fecha de nacimiento: " + r.getFechaNacimiento());
      System.out.println("Cantidad de maquinarias: " + r.cantidadMaquinarias());
    }
  }

  private static void eliminarResponsable() {
    System.out.println("\n--- Eliminar responsable ---");
    String identificacion = leerStringValido("Número de identificación: ");

    Responsable responsable = sistema.buscarResponsablePorIdentificacion(identificacion);
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
    System.out.println("Tipo: " + obtenerTipo(m));
    System.out.println("Marca: " + m.getMarca());
    System.out.println("Descripción: " + m.getDescripcion());
    System.out.println("Valor: " + m.getValor());
    System.out.println("Vida útil: " + m.getVidaUtil() + " años");
    if (m instanceof EquipoIndustrial) {
      EquipoIndustrial eq = (EquipoIndustrial) m;
      System.out.println("Peso: " + eq.getPeso());
      System.out.println("Volumen: " + eq.getVolumen());
    } else if (m instanceof HerramientaIndustrial) {
      HerramientaIndustrial her = (HerramientaIndustrial) m;
      System.out.println("Valor de salvamento: " + her.getValorSalvamento());
      System.out.println("Tasa de depreciación: " + her.getTasaDepreciacion());
    }
  }

  private static void imprimirTablaDepreciacion(String tabla) {
    if (tabla == null || tabla.isEmpty()) {
      System.out.println("No se pudo generar la tabla de depreciación.");
      return;
    }
    System.out.println(tabla);
  }

  private static String obtenerTipo(Maquinaria m) {
    if (m instanceof EquipoIndustrial) {
      return "Equipo Industrial";
    }
    return "Herramienta Industrial";
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
