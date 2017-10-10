In visual studio, value can be displayed when hover mouse on the variable.  
Sometimes when VS 2015 and 2017 work in the same laptop, this feature is not working properly.  
2 solution:  
1.  try to: Tools -> Options -> Debugger -> General -> enable the flag [Use Managed Compatibility Mode].  
2.  If 1 does not work, try to: Tools-> Import and Export Settings Wizard-> Reset all settings-> No, just reset settings, overwriting my current settings -> Choose a Default Collection of Settings.
Check this page: https://stackoverflow.com/questions/19108801/datatips-mouse-hover-over-variables-in-debug-mode-not-working-in-visual-studio 
