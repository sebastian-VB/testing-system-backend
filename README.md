# Sistema de exámenes con roles de usuario y administrador
- Se maneja dos roles para el inicio de sesion, cada rol realiza tareas diferentes.
- Tanto administrador como usuario, acceden y se registran a los mismos endpoints, dado a que son rutas de acceso público.
- `El administrador podrá`: añadir nueva categoría, nuevos exámenes, nuevas preguntas, actualizar estos mismos, eliminar categorias, exámenes y preguntas, listar estos mismos de acuerdo a los endpoints elaborados en el backend.
- `El usuario podrá`: ver las categorías de exámenes disponibles, ver los examenes disponibles para cada categoría  lisatr todos los examenes en general. A su vez, puede empezar los exámenes, podrá leer las instrucciones  dar el examen, dentro tendrá un cronómetro que indica el tiempo que tiene lara responder las preguntas y enviar, pasado ese tiempo el examen se envía solo.

`Importante`: para que pueda funcionar, en la carpera `resources` y en el archivo `application.properties`, se debe cambiar el nombre de la base de datos, el nombre de usuario y la contraseña.

`Se está trabajando con MYSQL`.

![image](https://github.com/sebastian-VB/testing-system-backend/assets/79015284/5d1e77f2-9343-4b64-a9ed-c278ef552739)
