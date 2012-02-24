## Description
The "Local File Compare Assist" plugin is an Eclipse plugin that helps you compare the same name file with other projects in the workspace. 
It will save some time before merging  when you are in the process of  the multi-version development.

(Frequently, I have to merge the modified file with other branches after added some features or fixed bugs.
It'a very annoying for me to repeat the prepare  actions before merging: first to open the search dialog to location the file, and then select the two files which need to compare. In the big projects, it's more annoying to drag the vertical scroll bar to select the files.)

## How it works
After the plugin loaded,  it will add a menu item to the popup menu whether you click the right menu above the "Package Explorer" view or in the current Editor view. The submenu will popup when mouse over the menu item. Obviously, the submenu are associate with the projects under current workspace. The compare editor will be opened if there is any different  with the editing file between current project and selected the project.

## Required Eclipse Version
It was built under Eclipse Indigo (v3.7) and tested under Eclipse Helios (v3.6.x) and Eclipse Indigo (v3.7).
Other versions have not been tested yet.

## License
This plugin is licensed under the [Eclipse Public License v1.0 (EPL)](http://www.eclipse.org/legal/epl-v10.html).

## Latest Version
The latest version is version 1.0.1. Updated on Feb 23, 2012.

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
* Fixed critical problem - the document cannot be updated after save the diff changes.
* Filter the unnecessary projects.
                      
### 1.0.1 (Feb 23, 2012)
* Support popup menu in "JSP Editor" view.
* Fixed the file path under windows OS.

### 1.0.0 (Feb 22, 2012)
* Initial the project
* Implement the basic features: popup menu in "Package Explorer" view, "Text Editor" view.