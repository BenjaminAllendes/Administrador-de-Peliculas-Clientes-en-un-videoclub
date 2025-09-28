# 🎬 Administrador de Peliculas-Clientes en un Videoclub

Este proyecto es una aplicación de escritorio en Java que simula la gestión de un videoclub. Permite a los usuarios administrar un catálogo de películas, registrar y gestionar clientes, y realizar un seguimiento de los arriendos y devoluciones. La aplicación utiliza una interfaz gráfica de usuario (GUI) simple y persiste los datos en archivos CSV para guardar el estado entre ejecuciones.

---

## ⚙️ Requisitos del Sistema

Para ejecutar este programa, necesitarás tener instalado:

* **Java Development Kit (JDK) 8** o superior.
* Un entorno de desarrollo integrado (IDE) como **IntelliJ IDEA** o **Eclipse** es recomendable para una fácil compilación y ejecución.

---

## 🚀 Cómo Usar el Programa

1.  **Clonar o descargar el proyecto:**
    Clona el repositorio desde GitHub o descarga el código fuente en tu máquina local.

2.  **Abrir el proyecto en un IDE:**
    Importa la carpeta del proyecto a tu IDE preferido. Asegúrate de que todas las clases están en el mismo paquete o en una estructura de paquetes lógica.

3.  **Compilar y ejecutar:**
    Ejecuta la clase `Main.java`. El programa se iniciará automáticamente y mostrará la interfaz gráfica.

4.  **Uso de la interfaz:**
    La interfaz principal consta de un panel de botones a la izquierda y un área de texto central.

    * **Panel de botones:** Cada botón corresponde a una función específica (registrar cliente, arrendar película, etc.).
    * **Área de texto:** Muestra los resultados de las operaciones, como la confirmación de un arriendo o la lista de películas.

5.  **Persistencia de datos:**
    El programa carga y guarda automáticamente el estado de las películas, clientes y arriendos en archivos CSV (`clients.csv`, `movies.csv`, `rents.csv`, `past_rents.csv`) al iniciar y al cerrar la aplicación. No necesitas manipular estos archivos directamente.

---

## ✨ Características Principales

* **Gestión de Clientes:** Permite registrar nuevos clientes y categorizarlos como **VIP** o **normales**.
* **Catálogo de Películas:** Muestra y gestiona un catálogo de películas, permitiendo **agregar nuevas películas** (normales o de estreno) y **actualizar su stock**.
* **Arriendos y Devoluciones:** Proporciona un flujo de trabajo para **arrendar películas** y **gestionarlas en el historial** de cada cliente.
* **Recomendaciones Personalizadas:** Sugiere películas a los clientes basándose en los géneros que han arrendado en el pasado.
* **Gestión Avanzada:** Incluye funcionalidades para **modificar y eliminar clientes**, ver perfiles detallados con su historial de arriendos.
* **Persistencia de Datos:** El estado del videoclub (clientes, películas y arriendos) se guarda y carga automáticamente al inicio y final de cada sesión.
* **Generación de Reportes:** Genera un archivo de texto (`reporte_videoclub.txt`) con un resumen completo del estado del sistema al salir.
* **Manejo de Errores:** Incluye excepciones personalizadas para gestionar situaciones como la falta de stock o la no existencia de un recurso, proporcionando mensajes de error claros al usuario.

---

## 🛠️ Estructura del Código

El proyecto está organizado en las siguientes clases, siguiendo los principios de la programación orientada a objetos (POO):

* `Main.java`: Punto de entrada de la aplicación.
* `VideoclubGUI.java`: Contiene la interfaz gráfica de usuario y maneja los eventos de los botones.
* `Videoclub.java`: La clase principal del modelo, que gestiona toda la lógica de negocio (clientes, películas, arriendos).
* `Movie.java` / `NewReleaseMovie.java`: Clases que representan las películas y el tipo de película.
* `Client.java` / `ClientVIP.java`: Clases que representan a los clientes y sus características.
* `Rent.java`: Representa el arriendo de una película por un cliente.
* `RecursoNoEncontradoException.java` / `PeliculaSinStockException.java`: Excepciones personalizadas para un manejo de errores robusto.

### Funcionalidades de los Botones ⚙️

---

#### **1. Registrarse**
Permite registrar a un nuevo cliente en el sistema. Al hacer clic, se te pedirá el nombre del cliente y se te dará la opción de registrarlo como **normal** o **VIP**. El sistema le asignará un ID único de forma automática.

---

#### **2. Realizar Arriendo**
Con esta función, puedes arrendar una película a un cliente. Deberás ingresar el ID del cliente, el ID de la película y los días de arriendo. La aplicación validará el stock disponible y, si todo es correcto, procesará el arriendo.

---

#### **3. Ver recomendaciones**
Muestra una lista de películas recomendadas para un cliente. El sistema analiza el historial de arriendos del cliente para identificar sus géneros favoritos y sugerir películas disponibles que aún no ha visto.

---

#### **4. Devolver Película**
Permite registrar la devolución de una película. Solo tienes que ingresar el ID del cliente y el ID de la película. El sistema verificará el arriendo, lo moverá al historial del cliente y devolverá la película al stock.

---

#### **5. Ver Arriendos Activos**
Muestra en pantalla una lista de todos los arriendos que están actualmente en curso. La información incluye el cliente, la película y la fecha de devolución.

---

#### **6. Ver Socios**
Genera y muestra una lista de todos los clientes registrados en el videoclub. La lista detalla el ID, el nombre y si el cliente es **VIP**.

---

#### **7. Ver Catálogo**
Muestra el catálogo completo de películas disponibles. La información incluye el ID, el título, el género, el stock y si la película es un **estreno**.

---

#### **8. Actualizar Stock**
Te permite aumentar el stock de una película. Deberás ingresar el ID de la película y la cantidad de unidades que deseas agregar.

---

#### **9. Gestionar Clientes**
Ofrece opciones avanzadas para administrar los datos de un cliente. Al ingresar el ID, podrás elegir entre **modificar su nombre** o **elim