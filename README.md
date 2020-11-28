# Pushe SDK for Unity game engine

## Pushe Plugin

Visit [**Pushe documentation**](https://docs.pushe.co/docs/unity/intro/) for usage of plugin in a project

## How to develop

### Add `Unity` to your PATH
Copy the address of `Unity.exe` in the Environment variables.

### Create a package

After modifying the source as you desired, you need to create a package.

Use Unity's [Command line arguements]() to make a package. Following command will create package:

```bash
# Output will be Pushe.unitypackage in the scripts folder
Unity -gvh_disable -batchmode -projectPath . -exportPackage Assets Pushe.unitypackage -quit
```