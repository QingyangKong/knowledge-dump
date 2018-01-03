# APT
APT(advanced packaging tool) is an evolution of debian. In ubuntu, it is preinstalled and used to download packages. `apt-get` is used to download package.  
`apt-get` needs to be updated otherwise it is not able to get correct version.  
### Remeber to update APT
eg: `sudo apt-get install software-properties-common`
```
WARNING: The following packages cannot be authenticated!
  software-properties-common software-properties-gtk
  python3-software-properties
Install these packages without verification? [y/N] y
Err http://us.archive.ubuntu.com/ubuntu/ trusty-updates/main software-properties-common all 0.92.37.6    
404  Not Found [IP: 91.189.91.23 80]
Err http://us.archive.ubuntu.com/ubuntu/ trusty-updates/main software-properties-gtk all 0.92.37.6   
404  Not Found [IP: 91.189.91.23 80]
Err http://us.archive.ubuntu.com/ubuntu/ trusty-updates/main python3-software-properties all 0.92.37.6  
404  Not Found [IP: 91.189.91.23 80]
E: Failed to fetch http://us.archive.ubuntu.com/ubuntu/pool/main/s/software-properties/software-properties-common_0.92.37.6_all.deb  
404  Not Found [IP: 91.189.91.23 80]
```
after update `sudo apt-get update`, the package can be installed successfully.

### Remeber to add repository
This is used to add personal package archive(PPA) to apt list. PPA are not released by third party instead of linux release. In order to use them, repostory of PPAs needs to be added, an apt update is required after the PPA added.  
eg:  
```
sudo add-apt-repository ppa:ehtereum/ehtereum
sudo add-apt-repository ppa:ehtereum/ehtereum-dev
sudo apt-get update
```
