## Description
The "Local File Compare Assist" plugin is an Eclipse plugin that helps you to compare the same name file with other projects in the workspace. It simplifies the "Compare With > Each Other" action for the same name files.

It will save some time before merging when you are in the process of the multi-version development.

(It's very annoying to use the "Compare With > Each Other" when working on the huge projects. They often have long file list, so you have to drag the vertical scroll bar to find out the files)

## How it works
Usually we can compare files to each other from the fast view.

After loaded this plugin, we can compare files not only from the "Package Explorer" fast view but also from the current TextEditor/JSPEditor.

The compare menu will be added to right key menu. Then we can compare current selected/editing file with the one in the specific project. 

## Required Eclipse Version
It was built under Eclipse Indigo (v3.7) and tested under Eclipse Helios (v3.6.x) and Eclipse Indigo (v3.7).
Other versions have not been tested yet.

## License
This plugin is licensed under the [Eclipse Public License v1.0 (EPL)](http://www.eclipse.org/legal/epl-v10.html).

## Latest Version
The latest version is version 1.0.2. Updated on Feb 24, 2012.

Features:

* Support right menu in "Package Explorer" view.
* Support right menu in "Text Editor" and "JSP Editor" view.

## Download
* [Version 1.0.2 - Download JAR](https://github.com/linxux/LocalFileCompareAssist/raw/master/build/plugins/com.ah.filecompareassist.plugin.eclipse_1.0.2.20120224.jar)
* [Version 1.0.2 - Download Java Source](https://github.com/linxux/LocalFileCompareAssist/raw/master/build/plugins/com.ah.filecompareassist.plugin.eclipse.source_1.0.2.20120224.jar)

Older Versions - [go here](https://github.com/linxux/LocalFileCompareAssist/tree/master/build/plugins)

## Installation

Manual installation

* Use the Eclipse "_dropins_" folder. [(Supported dropins formats)](http://wiki.eclipse.org/Equinox/p2/Getting_Started#Supported_dropins_formats)
* Use the Eclipse "_links_" folder.
* Use the Eclipse "_plugins_" folder. (This way is not recommended.)

Via the Eclipse p2 update manager (This will coming soon)


## Screenshots

## Version History
### 1.0.2 (Feb 24, 2012)
* Fixed the critical problem - the document cannot be updated after save the diff changes.
* Filter the unnecessary projects.
                      
### 1.0.1 (Feb 23, 2012)
* Support popup menu in "JSP Editor" view.
* Fixed the file path under windows OS.

### 1.0.0 (Feb 22, 2012)
* Initial the project
* Implement the basic features: popup menu in "Package Explorer" view, "Text Editor" view.