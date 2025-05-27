# 🐉 VigiBall App

Una aplicación móvil moderna y adaptativa para explorar a los personajes del universo de **Dragon Ball**, obtenidos desde la [Dragon Ball API](https://web.dragonball-api.com/).  
Diseñada con **Jetpack Compose**, **Kotlin** y **arquitectura limpia**, esta app te ofrece una experiencia fluida, sin bloqueos y completamente internacionalizada.

---

## ✨ Features

- 📜 **Lista de personajes** con:
  - Imagen oficial
  - Nombre
  - Afiliación
  - Ki
  - Ki Máximo
  - Raza
  - Género
  - Descripción
  - Transformaciones (si aplica)

- 🔍 **Barra de búsqueda** para encontrar personajes por nombre

- 🧭 **Navegación sencilla e intuitiva** mediante tarjetas y navegación por pantalla

- 🌐 **Internacionalización completa**:
  - Español (por defecto)
  - Inglés 🇺🇸
  - Alemán 🇩🇪
  - Japonés 🇯🇵
  - Ruso 🇷🇺

- 🧠 **Manejo de errores de red**: si no hay conexión o la API no responde, se muestra un mensaje de error amigable

- 🎯 **Diseño responsivo y adaptativo**: funciona perfectamente en dispositivos móviles y tablets

- 📲 **Botón de compartir** el proyecto en GitHub con tus contactos

- 🙋‍♂️ **Sección “Acerca de”**: Datos para contactar al desarrollador

---

## 🧪 Tecnologías utilizadas

| Categoría | Herramientas |
|----------|--------------|
| Lenguaje | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Navegación | Navigation Compose |
| Internacionalización | `strings.xml` con soporte multilenguaje |
| Consumo de API | Retrofit + Gson |
| Imágenes | Coil |
| Persistencia | DataStore |
| Manejo de estado | ViewModel, StateFlow |
| Otros | GitHub |

---

## 🖼️ Screenshots

<div style="text-align: center; margin-bottom: 20px;">
  <img src="https://github.com/user-attachments/assets/a0a46f37-1a4b-4fcd-b3e5-31d1231c29f1" alt="Light" width="300" />
  <div>Modo Light</div>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <img src="https://github.com/user-attachments/assets/5ba14852-8ab4-417f-ada2-32ce4d0bd9c5" alt="DarkMode" width="300" />
  <div>DarkMode</div>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <img src="https://github.com/user-attachments/assets/461d4ff8-e244-498c-b97a-4b17d102e421" alt="Search" width="300" />
  <div>Cuadro de búsqueda</div>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <img src="https://github.com/user-attachments/assets/aeae06c5-6aa1-4052-a317-3e5ccef86181" alt="Info" width="300" />
  <div>Cuadro de información del desarrollador</div>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <img src="https://github.com/user-attachments/assets/afd0e814-d67b-42af-ac0d-4d3963112fcc" alt="Character 1" width="300" />
  <div>Personaje en DarkMode</div>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <img src="https://github.com/user-attachments/assets/ddd724f5-bb9d-4bc4-812c-44183d6b821e" alt="Character 2" width="300" />
  <div>Personaje en LightMode</div>
</div>

---

## 🌍 Idiomas disponibles

- Español (es)
- English (en)
- Deutsch (de)
- 日本語 (ja)
- Русский (ru)

---
## 🚀 Cómo ejecutar el proyecto

### 📥 Opción 1: Descargar APK (Recomendado para prueba rápida)
1. **Descarga la última versión** desde [Releases](https://github.com/Alejandro-Vigi/VigiBall/releases/tag/v1.0)
2. **Instala el APK** en tu dispositivo Android:
   - Ve a la carpeta de descargas
   - Toca en `VigiBall-v1.0.apk`
   - Permite la instalación de apps de origen desconocido si es necesario
   - Sigue las instrucciones de instalación

### 👨💻 Opción 2: Clonar y compilar
1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/Alejandro-Vigi/VigiBall.git
2. **Abre el proyecto** en Android Studio
3. **Conecta tu dispositivo** Android o usa un emulador
4. **Ejecuta la app**:
   - Haz clic en el botón "Run" (▶️)
   - Selecciona tu dispositivo
   - Espera a que se instale y ejecute

## 📱 Requisitos
- Android 8.0 (Oreo) o superior
- 100MB de espacio libre
- Conexión a internet para cargar los datos

## ⚠️ Notas importantes
- La primera ejecución puede tardar unos segundos en cargar los personajes
- Si tienes problemas con el APK, verifica que tienes permitida la instalación de apps de origen desconocido
