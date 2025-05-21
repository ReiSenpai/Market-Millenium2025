## 🚀 Subir Cambios desde Local a GitHub

Sigue estos pasos para subir tus cambios locales al repositorio remoto en GitHub:

### 1. Verifica el estado del repositorio

Este comando te muestra los archivos modificados y pendientes de ser añadidos o confirmados:

```bash
git status
```
Para añadir todos los cambios:
```bash
git add .
```
O para añadir archivos específicos:
```bash
git add nombre-del-archivo
```
Crea un commit con un mensaje descriptivo:
```bash
git commit -m "Mensaje descriptivo de los cambios realizados"
```
Sube los cambios al repositorio remoto:
```bash
git push origin main
```
