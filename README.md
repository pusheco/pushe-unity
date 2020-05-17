# Implementation of Pushe plugin for Unity game engine

## Content

### Scripts

All C# scripts that are written to connect native code to engine scripts

### Unity-extended

Native plugin written in Java to be man in the middle for easy connection between native Pushe library and engine scripts. Issues like MultiDexing, Library excluding, etc. are handled in `unity-extended` plugin.


## Usage


### Add `Unity` to your PATH
Copy the address of `Unity.exe` in the Environment variables.

### Create a package
Use Unity's [Command line arguements]() to make a package. Following command will create package:

```bash
# Output will be Pushe.unitypackage in the scripts folder
Unity -gvh_disable -batchmode -projectPath scripts -exportPackage Assets Pushe.unitypackage -quit
```