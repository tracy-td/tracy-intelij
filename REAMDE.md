# Artifact Priority Sorter for Tracy-TD
####  Plugin for Jetbrains IDEs. Highlights project files in color according to their Tracy-TD classification.

When the developer is working,
it is necessary for him to realize which artifacts are most important to the business


The plugin allows the IntelliJ interface to classify artifacts with colors,
according to their criticality for the business.
The colors of the artifacts are changed according to the level
of priority and impact on the business: the artifacts that have the red
color have critical priority, they have a maximum level of priority.
Yellow artifacts are high priority, blue are medium priority,
and green are low priority.

![Highlight menu](images/tree-color-class-presentation.png)



Previously, there was only one way to do this:
create a scope, specify its name and filename pattern, then add a color
and attach it to the created scope, then click `Ok` and `Apply`
to see how it looks now. If the color doesn't fit, double-click it, select a new color,
click `OK` and `Apply` again to see the changes. If the color doesn't fit again -
repeat until it fits.
This is a rather inconvenient way (and takes a lot of time).

**ProjectTree Color Highlighter** was made to get rid of this inconvenience.
Highlight your files and folders with ease using the context menu. Adjust the colors
using the color picker, get an instant preview of the project tree and editor tabs
while adjusting.