package com.productos.crud_hbomax;

import com.productos.crud.crud_Episodios;
import com.productos.crud.crud_Historial_Reproduccion;
import com.productos.crud.crud_Peliculas;
import com.productos.crud.crud_Perfiles;
import com.productos.crud.crud_Series;
import com.productos.crud.crud_Temporadas;
import com.productos.crud.crud_Usuarios;
import com.productos.entities.Episodios;
import com.productos.entities.Historial_Reproduccion;
import com.productos.entities.Peliculas;
import com.productos.entities.Perfiles;
import com.productos.entities.Series;
import com.productos.entities.Temporadas;
import com.productos.entities.Usuarios;
import java.util.List;
import java.util.Scanner;

public class CRUD_HBOMax {

    public static void main(String[] args) {
        crud_Series crudSerie = new crud_Series();
        crud_Peliculas crudPeli = new crud_Peliculas();
        crud_Temporadas crudTem = new crud_Temporadas();
        crud_Episodios crudEpi = new crud_Episodios();
        crud_Usuarios crudUser = new crud_Usuarios();
        crud_Perfiles crudPer = new crud_Perfiles();
        crud_Historial_Reproduccion crudHist = new crud_Historial_Reproduccion();
        Scanner teclado = new Scanner(System.in);
        int opcionGeneral = 0;
        do {
            System.out.println("\n=================================");
            System.out.println("    SISTEMA DE GESTION MAX       ");
            System.out.println("=================================");
            System.out.println("1. Administrar Peliculas");
            System.out.println("2. Administrar Series");
            System.out.println("3. Administrar Temporadas");
            System.out.println("4. Administrar Episodios");
            System.out.println("5. Administrar Usuarios");
            System.out.println("6. Administrar Perfiles");
            System.out.println("7. Administrar Historial de Reproduccion");
            System.out.println("8. Salir del Sistema");
            System.out.print("Seleccione una opcion general: ");

            try {
                opcionGeneral = teclado.nextInt();
                teclado.nextLine();

                switch (opcionGeneral) {
                    case 1:
                        menuPeliculas(teclado, crudPeli);
                        break;
                    case 2:
                        menuSeries(teclado, crudSerie);
                        break;
                    case 3:
                        menuTempordas(teclado, crudTem, crudSerie);
                        break;
                    case 4:
                        menuEpisodios(teclado, crudEpi, crudTem);
                        break;
                    case 5:
                        menuUsuarios(teclado, crudUser);
                        break;
                    case 6:
                        menuPerfiles(teclado, crudPer, crudUser);
                        break;
                    case 7:
                        menuHistorial(teclado, crudHist, crudPer, crudPeli, crudEpi);
                        break;
                    case 8:
                        System.out.println("\nGracias por utilizar el sistema de gestion de HBOMax! Cerrando...");
                        break;
                    default:
                        System.out.println("Opción invalida. Intente de nuevo.");
                        teclado.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Error: Por favor introduzca un numero valido.");
                teclado.nextLine();
            }

        } while (opcionGeneral != 8);

        teclado.close();
    }

    private static void menuPeliculas(Scanner teclado, crud_Peliculas crudPeli) {
        int opcionSub = 0;
        do {
            System.out.println("\n--- GESTION DE PELICULAS ---");
            System.out.println("1. Registrar nueva Pelicula");
            System.out.println("2. Listar todas las Peliculas");
            System.out.println("3. Modificar una Pelicula por ID");
            System.out.println("4. Eliminar una Pelicula por ID");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            opcionSub = teclado.nextInt();
            teclado.nextLine(); // Limpiar búfer

            switch (opcionSub) {
                case 1:
                    System.out.println("\nRegistrar la Pelicula");
                    System.out.print("Ingrese el nombre de la pelicula: ");
                    String nom = teclado.nextLine();
                    System.out.print("Ingrese el genero: ");
                    String gen = teclado.nextLine();
                    System.out.print("Ingrese la fecha de estreno (DD/MM/AAAA): ");
                    String fec = teclado.nextLine();
                    System.out.print("Ingrese la duracion (en minutos): ");
                    int dur = teclado.nextInt();
                    teclado.nextLine(); // Limpiar búfer

                    Peliculas nuevaPeli = new Peliculas(nom, gen, fec, dur);
                    crudPeli.crear(nuevaPeli);
                    break;

                case 2:
                    System.out.println("\nCatalogo de Peliculas");
                    List<Peliculas> peliculas = crudPeli.leerTodas();
                    if (peliculas.isEmpty()) {
                        System.out.println("No hay peliculas registradas en la base de datos.");
                    } else {
                        System.out.println("---------------------------------------------------------------------------------------");
                        for (Peliculas p : peliculas) {
                            System.out.println("ID: " + p.getId() + " | Titulo: " + p.getTitulo() + " | Genero: " + p.getGenero() + " | Estreno: " + p.getFecha_Estreno() + " | Duracion: " + p.getTiempo_Min() + " min.");
                        }
                        System.out.println("---------------------------------------------------------------------------------------");
                    }
                    break;

                case 3:
                    System.out.println("\nModificar Pelicula");
                    System.out.print("Ingrese el ID de la pelicula a actualizar: ");
                    int idMod = teclado.nextInt();
                    teclado.nextLine();

                    System.out.print("Nuevo nombre de la pelicula: ");
                    String nuevoNom = teclado.nextLine();
                    System.out.print("Nuevo genero: ");
                    String nuevoGen = teclado.nextLine();
                    System.out.print("Nueva fecha de estreno: ");
                    String nuevaFec = teclado.nextLine();
                    System.out.print("Nueva duracion (en minutos): ");
                    int nuevaDur = teclado.nextInt();
                    teclado.nextLine();

                    Peliculas peliModificada = new Peliculas(nuevoNom, nuevoGen, nuevaFec, nuevaDur);
                    peliModificada.setId(idMod);
                    crudPeli.actualizar(peliModificada);
                    break;

                case 4:
                    System.out.println("\nEliminar Pelicula");
                    System.out.print("Ingrese el ID de la pelicula a eliminar: ");
                    int idEli = teclado.nextInt();
                    teclado.nextLine();

                    crudPeli.eliminar(idEli);
                    break;

                case 5:
                    System.out.println("Regresando al menu principal...");
                    break;

                default:
                    System.out.println("Opción invalida.");
            }
        } while (opcionSub != 5);
    }

    private static void menuSeries(Scanner teclado, crud_Series crudSerie) {
        int opcionSub = 0;
        do {
            System.out.println("\n--- GESTION DE SERIES ---");
            System.out.println("1. Registrar nueva Serie");
            System.out.println("2. Listar todas las Series");
            System.out.println("3. Modificar una Serie por ID");
            System.out.println("4. Eliminar una Serie por ID");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            opcionSub = teclado.nextInt();
            teclado.nextLine();

            switch (opcionSub) {
                case 1:
                    System.out.println("\nRegistre la Serie");
                    System.out.print("Ingrese el nombre de la serie: ");
                    String nombreSer = teclado.nextLine();
                    teclado.nextLine();

                    Series nuevaSerie = new Series(nombreSer);
                    crudSerie.crear(nuevaSerie);
                    break;

                case 2:
                    System.out.println("\nCatalogo de Series");
                    List<Series> series = crudSerie.leerTodas();
                    if (series.isEmpty()) {
                        System.out.println("No hay series registradas en la base de datos.");
                    } else {
                        System.out.println("----------------------------------------");
                        for (Series s : series) {
                            System.out.println("ID: " + s.getId() + " | Titulo: " + s.getNombre());
                        }
                        System.out.println("----------------------------------------");
                    }
                    break;

                case 3:
                    System.out.println("\nModificar la Serie");
                    System.out.print("Ingrese el ID de la serie a actualizar: ");
                    int idMod = teclado.nextInt();
                    teclado.nextLine();

                    System.out.print("Ingrese el nuevo titulo de la serie: ");
                    String nuevoNombre = teclado.nextLine();

                    crudSerie.modificar(idMod, nuevoNombre);
                    break;

                case 4:
                    System.out.println("\nEliminar la Serie");
                    System.out.print("Ingrese el ID de la serie a eliminar: ");
                    int idEli = teclado.nextInt();
                    teclado.nextLine();

                    crudSerie.eliminar(idEli);
                    break;

                case 5:
                    System.out.println("Regresando al menu principal...");
                    break;

                default:
                    System.out.println("Opción invalida.");
            }
        } while (opcionSub != 5);
    }

    private static void menuTempordas(Scanner teclado, crud_Temporadas crudTem, crud_Series crudSerie) {
        int opcionSub = 0;
        do {
            System.out.println("\n--- GESTION DE TEMPORADAS ---");
            System.out.println("1. Registrar nueva Temporada");
            System.out.println("2. Listar todas las Temporadas");
            System.out.println("3. Modificar una Temporada por ID");
            System.out.println("4. Eliminar una Temporada por ID");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            if (teclado.hasNextInt()) {
                opcionSub = teclado.nextInt();
                teclado.nextLine(); // Limpiar búfer

                switch (opcionSub) {
                    case 1:
                        System.out.println("\nRegistrar un Nueva Temporada");
                        System.out.print("Ingrese el ID de la Serie a la que pertenece: ");
                        int idSer = teclado.nextInt();
                        teclado.nextLine();

                        Series serieEncontrada = crudSerie.buscarPorId(idSer);
                        if (serieEncontrada != null) {
                            System.out.print("Ingrese el numero de temporada (ej. 1, 2): ");
                            int numTom = teclado.nextInt();
                            teclado.nextLine();

                            Temporadas nuevaTempo = new Temporadas();
                            nuevaTempo.setNumeroTemporada(numTom);
                            nuevaTempo.setSerie(serieEncontrada);

                            crudTem.crear(nuevaTempo);
                        } else {
                            System.out.println("Error: No existe ninguna serie con el ID proporcionado.");
                        }
                        break;

                    case 2:
                        System.out.println("\nCatalogo de Temporadas");
                        List<Temporadas> listaTempo = crudTem.leerTodas();
                        if (listaTempo.isEmpty()) {
                            System.out.println("No hay temporadas registradas.");
                        } else {
                            System.out.println("----------------------------------------");
                            for (Temporadas t : listaTempo) {
                                String nombreSerie = (t.getSerie() != null) ? t.getSerie().getNombre() : "Sin Serie";
                                System.out.println("ID: " + t.getId() + " | Num. Temporada: " + t.getNumeroTemporada() + " | Serie: " + nombreSerie);
                            }
                            System.out.println("----------------------------------------");
                        }
                        break;

                    case 3:
                        System.out.println("\nModificar Temporada");
                        System.out.print("Ingrese el ID de la temporada a actualizar: ");
                        int idModTempo = teclado.nextInt();
                        teclado.nextLine();

                        Temporadas tempoModificada = crudTem.buscarPorId(idModTempo);
                        if (tempoModificada != null) {
                            System.out.print("Nuevo numero de temporada: ");
                            int nuevoNum = teclado.nextInt();
                            teclado.nextLine();

                            System.out.print("Desea cambiar la serie asociada? Ingrese el nuevo ID de Serie (o el mismo para mantenerla): ");
                            int idNuevaSer = teclado.nextInt();
                            teclado.nextLine();

                            Series nuevaSer = crudSerie.buscarPorId(idNuevaSer);
                            if (nuevaSer != null) {
                                tempoModificada.setNumeroTemporada(nuevoNum);
                                tempoModificada.setSerie(nuevaSer);
                                crudTem.actualizar(tempoModificada);
                            } else {
                                System.out.println("Error: El ID de la serie proporcionada no existe.");
                            }
                        } else {
                            System.out.println("Error: No se encontro la temporada con ese ID.");
                        }
                        break;

                    case 4:
                        System.out.println("\nEliminar Temporada]");
                        System.out.print("Ingrese el ID de la temporada a eliminar: ");
                        int idEliTempo = teclado.nextInt();
                        teclado.nextLine();

                        crudTem.eliminar(idEliTempo);
                        break;

                    case 5:
                        System.out.println("Regresando al menu principal...");
                        break;

                    default:
                        System.out.println("Opcion invalida.");
                }
            } else {
                System.out.println("Error: Ingrese un numero valido.");
                teclado.nextLine();
            }
        } while (opcionSub != 5);
    }

    private static void menuEpisodios(Scanner teclado, crud_Episodios crudEpi, crud_Temporadas crudTem) {
        int opcionSub = 0;
        do {
            System.out.println("\n--- GESTION DE EPISODIOS ---");
            System.out.println("1. Registrar nuevo Episodio");
            System.out.println("2. Listar todos los Episodios");
            System.out.println("3. Modificar un Episodio por ID");
            System.out.println("4. Eliminar un Episodio por ID");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            if (teclado.hasNextInt()) {
                opcionSub = teclado.nextInt();
                teclado.nextLine(); // Limpiar búfer

                switch (opcionSub) {
                    case 1:
                        System.out.println("\nRegistrar Episodio");
                        System.out.print("Ingrese el ID de la Temporada a la que pertenece: ");
                        int idTom = teclado.nextInt();
                        teclado.nextLine();

                        Temporadas tempoEncontrada = crudTem.buscarPorId(idTom);
                        if (tempoEncontrada != null) {
                            System.out.print("Ingrese el numero de episodio: ");
                            int numEpi = teclado.nextInt();
                            teclado.nextLine();
                            System.out.print("Ingrese el nombre del episodio: ");
                            String nombreEpi = teclado.nextLine();
                            System.out.print("Ingrese la duracion (en minutos): ");
                            int duracionEpi = teclado.nextInt();
                            teclado.nextLine();

                            Episodios nuevoEpisodio = new Episodios();
                            nuevoEpisodio.setNumeroEpisodio(numEpi);
                            nuevoEpisodio.setNombre(nombreEpi);
                            nuevoEpisodio.setDuracion(duracionEpi);
                            nuevoEpisodio.setTemporada(tempoEncontrada);

                            crudEpi.crear(nuevoEpisodio);
                        } else {
                            System.out.println("Error: No existe ninguna temporada con el ID proporcionado.");
                        }
                        break;

                    case 2:
                        System.out.println("\nCatalogo de Episodios");
                        List<Episodios> listaEpi = crudEpi.leerTodos();
                        if (listaEpi.isEmpty()) {
                            System.out.println("No hay episodios registrados.");
                        } else {
                            System.out.println("--------------------------------------------------------------------------------");
                            for (Episodios e : listaEpi) {
                                int numTempo = (e.getTemporada() != null) ? e.getTemporada().getNumeroTemporada() : 0;
                                String nombreSerie = (e.getTemporada() != null && e.getTemporada().getSerie() != null) ? e.getTemporada().getSerie().getNombre() : "Sin Serie";
                                System.out.println("ID: " + e.getId() + " | Serie: " + nombreSerie + " | Temp: " + numTempo + " | Cap: " + e.getNumeroEpisodio() + " - " + e.getNombre() + " (" + e.getDuracion() + " min.)");
                            }
                            System.out.println("--------------------------------------------------------------------------------");
                        }
                        break;

                    case 3:
                        System.out.println("\nModificar Episodio");
                        System.out.print("Ingrese el ID del episodio a actualizar: ");
                        int idModEpi = teclado.nextInt();
                        teclado.nextLine();

                        Episodios epiModificado = crudEpi.buscarPorId(idModEpi);
                        if (epiModificado != null) {
                            System.out.print("Nuevo numero de episodio: ");
                            int nuevoNum = teclado.nextInt();
                            teclado.nextLine();
                            System.out.print("Nuevo nombre del episodio: ");
                            String nuevoNom = teclado.nextLine();
                            System.out.print("Nueva duracion (en minutos): ");
                            int nuevaDur = teclado.nextInt();
                            teclado.nextLine();

                            System.out.print("Desea cambiar la temporada? Ingrese el ID de la nueva Temporada (o el mismo para mantenerla): ");
                            int idNuevaTempo = teclado.nextInt();
                            teclado.nextLine();

                            Temporadas nuevaTempo = crudTem.buscarPorId(idNuevaTempo);
                            if (nuevaTempo != null) {
                                epiModificado.setNumeroEpisodio(nuevoNum);
                                epiModificado.setNombre(nuevoNom);
                                epiModificado.setDuracion(nuevaDur);
                                epiModificado.setTemporada(nuevaTempo);

                                crudEpi.actualizar(epiModificado);
                            } else {
                                System.out.println("Error: El ID de la temporada proporcionada no existe.");
                            }
                        } else {
                            System.out.println("Error: No se encontro el episodio con ese ID.");
                        }
                        break;

                    case 4:
                        System.out.println("\nEliminar Episodio");
                        System.out.print("Ingrese el ID del episodio a eliminar: ");
                        int idEliEpi = teclado.nextInt();
                        teclado.nextLine();

                        crudEpi.eliminar(idEliEpi);
                        break;

                    case 5:
                        System.out.println("Regresando al menu principal...");
                        break;

                    default:
                        System.out.println("Opcion invalida.");
                }
            } else {
                System.out.println("Error: Ingrese un numero valido.");
                teclado.nextLine();
            }
        } while (opcionSub != 5);
    }

    private static void menuUsuarios(Scanner teclado, crud_Usuarios crudUser) {
        int opcionSub = 0;
        do {
            System.out.println("\n--- GESTION DE USUARIOS ---");
            System.out.println("1. Registrar nuevo Usuario");
            System.out.println("2. Listar todos los Usuarios");
            System.out.println("3. Modificar un Usuario por ID");
            System.out.println("4. Eliminar un Usuario por ID");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            if (teclado.hasNextInt()) {
                opcionSub = teclado.nextInt();
                teclado.nextLine(); // Limpiar búfer

                switch (opcionSub) {
                    case 1:
                        System.out.println("\nRegistrar Usuario");
                        System.out.print("Ingrese el nombre completo del usuario: ");
                        String nombres = teclado.nextLine();
                        System.out.print("Ingrese el correo electronico (email): ");
                        String email = teclado.nextLine();
                        String password = "";
                        java.io.Console consola = System.console();
                        if (consola != null) {
                            System.out.print("Ingrese la passwork (no se mostrara en pantalla): ");
                            char[] passwordChars = consola.readPassword();
                            password = new String(passwordChars);
                        } else {
                            System.out.print("Ingrese la passwork: ");
                            password = teclado.nextLine();
                        }
                        System.out.print("Ingrese el estado de la cuenta (Activo/Suspendido): ");
                        String estado = teclado.nextLine();

                        Usuarios nuevoUsuario = new Usuarios(nombres, email, password, estado);
                        crudUser.crear(nuevoUsuario);
                        break;

                    case 2:
                        System.out.println("\nListado de Cuentas de Usuarios]");
                        List<Usuarios> listaUser = crudUser.leerTodos();
                        if (listaUser.isEmpty()) {
                            System.out.println("No hay usuarios registrados en el sistema.");
                        } else {
                            System.out.println("--------------------------------------------------------------------------------");
                            for (Usuarios u : listaUser) {
                                System.out.println("ID: " + u.getId() + " | Nombre: " + u.getNombres() + " | Email: " + u.getEmail() + " | Estado: " + u.getEstado());
                            }
                            System.out.println("--------------------------------------------------------------------------------");
                        }
                        break;

                    case 3:
                        System.out.println("\nModificar Usuario");
                        System.out.print("Ingrese el ID del usuario a actualizar: ");
                        int idModUser = teclado.nextInt();
                        teclado.nextLine();

                        Usuarios userModificado = crudUser.buscarPorId(idModUser);
                        if (userModificado != null) {
                            System.out.print("Nuevo nombres del usuario: ");
                            String nuevoNombres = teclado.nextLine();
                            System.out.print("Nuevo correo electronico: ");
                            String nuevoEmail = teclado.nextLine();
                            String nuevoPass = "";
                            java.io.Console consolaMod = System.console();
                            if (consolaMod != null) {
                                System.out.print("Ingrese la nueva passwork (no se mostrara en pantalla): ");
                                char[] passwordChars = consolaMod.readPassword();
                                nuevoPass = new String(passwordChars);
                            } else {
                                // Respaldo para la consola emulada de NetBeans
                                System.out.print("Ingrese la nueva passwork: ");
                                nuevoPass = teclado.nextLine();
                            }
                            System.out.print("Nuevo estado de la cuenta (Activo/Suspendido): ");
                            String nuevoEstado = teclado.nextLine();

                            userModificado.setNombres(nuevoNombres);
                            userModificado.setEmail(nuevoEmail);
                            userModificado.setPassword(nuevoPass);
                            userModificado.setEstado(nuevoEstado);

                            crudUser.actualizar(userModificado);
                        } else {
                            System.out.println("Error: No se encontro el usuario con ese ID.");
                        }
                        break;

                    case 4:
                        System.out.println("\nEliminar Usuario");
                        System.out.print("Ingrese el ID del usuario a eliminar: ");
                        int idEliUser = teclado.nextInt();
                        teclado.nextLine();

                        crudUser.eliminar(idEliUser);
                        break;

                    case 5:
                        System.out.println("Regresando al menu principal...");
                        break;

                    default:
                        System.out.println("Opcion invalida.");
                }
            } else {
                System.out.println("Error: Ingrese un numero valido.");
                teclado.nextLine();
            }
        } while (opcionSub != 5);
    }

    private static void menuPerfiles(Scanner teclado, crud_Perfiles crudPerf, crud_Usuarios crudUser) {
        int opcionSub = 0;
        do {
            System.out.println("\n--- GESTION DE PERFILES ---");
            System.out.println("1. Registrar nuevo Perfil");
            System.out.println("2. Listar todos los Perfiles");
            System.out.println("3. Modificar un Perfil por ID");
            System.out.println("4. Eliminar un Perfil por ID");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            if (teclado.hasNextInt()) {
                opcionSub = teclado.nextInt();
                teclado.nextLine();

                switch (opcionSub) {
                    case 1:
                        System.out.println("\nRegistrar el Perfil");
                        System.out.print("Ingrese el ID del Usuario (Cuenta principal) al que pertenece: ");
                        int idUser = teclado.nextInt();
                        teclado.nextLine();

                        Usuarios userEncontrado = crudUser.buscarPorId(idUser);
                        if (userEncontrado != null) {
                            System.out.print("Ingrese el nombre del perfil: ");
                            String nombrePerf = teclado.nextLine();
                            System.out.print("Es un perfil infantil? (true/false): ");
                            boolean esKids = teclado.nextBoolean();
                            teclado.nextLine();

                            Perfiles nuevoPerfil = new Perfiles(nombrePerf, esKids, userEncontrado);
                            crudPerf.crear(nuevoPerfil);
                        } else {
                            System.out.println("Error: No existe ninguna cuenta de usuario con el ID proporcionado.");
                        }
                        break;

                    case 2:
                        System.out.println("\nListado de Perfiles");
                        List<Perfiles> listaPerf = crudPerf.leerTodos();
                        if (listaPerf.isEmpty()) {
                            System.out.println("No hay perfiles registrados en el sistema.");
                        } else {
                            System.out.println("--------------------------------------------------------------------------------");
                            for (Perfiles p : listaPerf) {
                                String emailUser = (p.getUsuario() != null) ? p.getUsuario().getEmail() : "Sin Cuenta";
                                System.out.println("ID Perfil: " + p.getId() + " | Nombre: " + p.getNombre() + " | Infantil: " + p.isEsKids() + " | Cuenta Asociada: " + emailUser);
                            }
                            System.out.println("--------------------------------------------------------------------------------");
                        }
                        break;

                    case 3:
                        System.out.println("\nModificar Perfil");
                        System.out.print("Ingrese el ID del perfil a actualizar: ");
                        int idModPerf = teclado.nextInt();
                        teclado.nextLine();

                        Perfiles perfModificado = crudPerf.buscarPorId(idModPerf);
                        if (perfModificado != null) {
                            System.out.print("Nuevo nombre del perfil: ");
                            String nuevoNom = teclado.nextLine();
                            System.out.print("¿Es perfil infantil? (true/false): ");
                            boolean nuevoEsKids = teclado.nextBoolean();
                            teclado.nextLine();

                            System.out.print("¿Desea cambiar la cuenta asociada? Ingrese el ID del Usuario (o el mismo para mantenerlo): ");
                            int idNuevoUser = teclado.nextInt();
                            teclado.nextLine();

                            Usuarios nuevoUser = crudUser.buscarPorId(idNuevoUser);
                            if (nuevoUser != null) {
                                perfModificado.setNombre(nuevoNom);
                                perfModificado.setEsKids(nuevoEsKids);
                                perfModificado.setUsuario(nuevoUser);

                                crudPerf.actualizar(perfModificado);
                            } else {
                                System.out.println("Error: El ID del usuario proporcionado no existe.");
                            }
                        } else {
                            System.out.println("Error: No se encontro el perfil con ese ID.");
                        }
                        break;

                    case 4:
                        System.out.println("\nEliminar Perfil");
                        System.out.print("Ingrese el ID del perfil a eliminar: ");
                        int idEliPerf = teclado.nextInt();
                        teclado.nextLine();

                        crudPerf.eliminar(idEliPerf);
                        break;

                    case 5:
                        System.out.println("Regresando al menu principal...");
                        break;

                    default:
                        System.out.println("Opcion invalida.");
                }
            } else {
                System.out.println("Error: Ingrese un numero valido.");
                teclado.nextLine();
            }
        } while (opcionSub != 5);
    }

    private static void menuHistorial(Scanner teclado, crud_Historial_Reproduccion crudHist, crud_Perfiles crudPerf, crud_Peliculas crudPeli, crud_Episodios crudEpi) {
        int opcionSub = 0;
        do {
            System.out.println("\n--- GESTION DE HISTORIAL DE REPRODUCCION ---");
            System.out.println("1. Registrar nuevo Progreso (Play/Pausa)");
            System.out.println("2. Ver todo el Historial");
            System.out.println("3. Actualizar Minuto de Pausa por ID");
            System.out.println("4. Eliminar Registro de Historial");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            if (teclado.hasNextInt()) {
                opcionSub = teclado.nextInt();
                teclado.nextLine();

                switch (opcionSub) {
                    case 1:
                        System.out.println("\nRegistro del Historial de Reproduccion");
                        System.out.print("Ingrese el ID del Perfil que esta viendo el contenido: ");
                        int idPerf = teclado.nextInt();
                        teclado.nextLine();

                        Perfiles perfEncontrado = crudPerf.buscarPorId(idPerf);
                        if (perfEncontrado != null) {
                            System.out.print("Ingrese el minuto de pausa actual: ");
                            int minPausa = teclado.nextInt();
                            teclado.nextLine();
                            System.out.print("Ingrese la fecha de reproduccion (DD/MM/AAAA): ");
                            String fecha = teclado.nextLine();
                            System.out.print("Vio el contenido completo? (true/false): ");
                            boolean completo = teclado.nextBoolean();
                            teclado.nextLine();

                            Historial_Reproduccion nuevoHist = new Historial_Reproduccion(minPausa, fecha, completo, perfEncontrado);

                            System.out.println("Que tipo de contenido esta viendo? (1. Pelicula | 2. Episodio de Serie): ");
                            int tipoContenido = teclado.nextInt();
                            teclado.nextLine();

                            if (tipoContenido == 1) {
                                System.out.print("Ingrese el ID de la Pelicula: ");
                                int idPeli = teclado.nextInt();
                                teclado.nextLine();
                                Peliculas peli = crudPeli.buscarPorId(idPeli);
                                if (peli != null) {
                                    nuevoHist.setPelicula(peli);
                                    crudHist.crear(nuevoHist);
                                } else {
                                    System.out.println("Error: La pelicula no existe.");
                                }
                            } else if (tipoContenido == 2) {
                                System.out.print("Ingrese el ID del Episodio: ");
                                int idEpi = teclado.nextInt();
                                teclado.nextLine();
                                Episodios epi = crudEpi.buscarPorId(idEpi);
                                if (epi != null) {
                                    nuevoHist.setEpisodio(epi);
                                    crudHist.crear(nuevoHist);
                                } else {
                                    System.out.println("Error: El episodio no existe.");
                                }
                            } else {
                                System.out.println("Opcion de contenido invalida. No se guardo el registro.");
                            }
                        } else {
                            System.out.println("Error: El ID de Perfil proporcionado no existe.");
                        }
                        break;

                    case 2:
                        System.out.println("\nHistorial total de Reproduccion");
                        List<Historial_Reproduccion> listaHist = crudHist.leerTodos();
                        if (listaHist.isEmpty()) {
                            System.out.println("No hay registros en el historial.");
                        } else {
                            System.out.println("--------------------------------------------------------------------------------");
                            for (Historial_Reproduccion h : listaHist) {
                                String nombrePerf = (h.getPerfil() != null) ? h.getPerfil().getNombre() : "Anonimo";
                                String contenido = "Contenido desconocido";
                                if (h.getPelicula() != null) {
                                    contenido = "Pelicula: " + h.getPelicula().getTitulo();
                                } else if (h.getEpisodio() != null) {
                                    contenido = "Episodio: " + h.getEpisodio().getNombre();
                                }
                                System.out.println("ID Historial: " + h.getId() + " | Perfil: " + nombrePerf + " | Viendo: " + contenido + " | Min. Pausa: " + h.getMinutoPausa() + " | Fecha: " + h.getFechaUltimaVista());
                            }
                            System.out.println("--------------------------------------------------------------------------------");
                        }
                        break;

                    case 3:
                        System.out.println("\nActualizar Progreso");
                        System.out.print("Ingrese el ID del registro de historial a modificar: ");
                        int idModHist = teclado.nextInt();
                        teclado.nextLine(); // Limpiar búfer

                        Historial_Reproduccion histModificar = crudHist.buscarPorId(idModHist);
                        if (histModificar != null) {
                            System.out.print("Ingrese el nuevo minuto de pausa: ");
                            int nuevoMin = teclado.nextInt();
                            teclado.nextLine();
                            System.out.print("Visto completo ahora? (true/false): ");
                            boolean nuevoCompleto = teclado.nextBoolean();
                            teclado.nextLine();
                            System.out.print("Ingrese la nueva fecha de reproduccion (DD/MM/AAAA): ");
                            String nuevaFecha = teclado.nextLine();

                            histModificar.setMinutoPausa(nuevoMin);
                            histModificar.setVistoCompleto(nuevoCompleto);
                            histModificar.setFechaUltimaVista(nuevaFecha);

                            System.out.println("Desea cambiar el contenido? (1. Pelicula | 2. Episodio | 3. Mantener Actual): ");
                            int opcionContenido = teclado.nextInt();
                            teclado.nextLine();

                            if (opcionContenido == 1) {
                                System.out.print("Ingrese el ID de la nueva Pelicula: ");
                                int idPeli = teclado.nextInt();
                                teclado.nextLine();
                                Peliculas peli = crudPeli.buscarPorId(idPeli);
                                if (peli != null) {
                                    histModificar.setPelicula(peli);
                                    histModificar.setEpisodio(null);
                                    crudHist.actualizar(histModificar);
                                } else {
                                    System.out.println("Error: La pelicula no existe. No se guardaron los cambios de contenido.");
                                }
                            } else if (opcionContenido == 2) {
                                System.out.print("Ingrese el ID del nuevo Episodio: ");
                                int idEpi = teclado.nextInt();
                                teclado.nextLine();
                                Episodios epi = crudEpi.buscarPorId(idEpi);
                                if (epi != null) {
                                    histModificar.setEpisodio(epi);
                                    histModificar.setPelicula(null);
                                    crudHist.actualizar(histModificar);
                                } else {
                                    System.out.println("Error: El episodio no existe. No se guardaron los cambios de contenido.");
                                }
                            } else if (opcionContenido == 3) {
                                crudHist.actualizar(histModificar);
                            } else {
                                System.out.println("Opcion invalida. No se procesaron los cambios.");
                            }
                        } else {
                            System.out.println("Error: Registro de historial no encontrado.");
                        }
                        break;

                    case 4:
                        System.out.println("\nEliminar Registro de Historial");
                        System.out.print("Ingrese el ID del registro de historial a eliminar: ");
                        try {
                            if (teclado.hasNextInt()) {
                                int idEliHist = teclado.nextInt();
                                teclado.nextLine();

                                crudHist.eliminar(idEliHist);
                            } else {
                                System.out.println("Error: Por favor, ingrese un número de ID válido.");
                                teclado.nextLine();
                            }
                        } catch (Exception ex) {
                            System.out.println("Error al procesar la eliminación: " + ex.getMessage());
                            teclado.nextLine();
                        }
                        break;
                    case 5:
                        System.out.println("Regresando al menu principal...");
                        break;

                    default:
                        System.out.println("Opcion invalida.");
                }
            } else {
                System.out.println("Error: Ingrese un numero valido.");
                teclado.nextLine();
            }
        } while (opcionSub != 5);
    }
}
