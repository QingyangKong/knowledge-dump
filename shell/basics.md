#### 1. variable and command substitution
$() to quote commands  
${} to quote varaible  
They are be nested
back tide can be also used but that is the old style.  

#### 2. execute file directly or using `source`
In nutshell, `source` executes file in current file. Executing file directly creates a new shell.
Exmaple:  
create a.sh as below:
```
export CP="a"
```
create b.sh as below:
```
source /path/a.sh
echo $CP
```
run the b.sh and `$CP` can be defined, so he result is `a`.  
If the b.sh is as below:
```
/path/a.sh
echo $CP
```
a is executed in a new shell and `$CP` cannot be defined in current shell, so result is empty. dot is the same as `source`. `source /path/a.sh` can also be written as `. /path/a.sh`.  
Usually `source` is used to load environment vars before executing the a shell. dot has a better compatibility than `source`.  

#### 3. get the path of current path
use command `dirname`
Usually a shell want to have path of itself because the shell is going to be used in the following.  
example:
```
sbin="`dirname "$0"`"
sbin="`cd "$sbin"; pwd`"
echo "$sbin"
```
$0 is the file name when the shell is executed.
```
FileName=${BASH_SOURCE[0]}
path=$(dirname "$FileName")
cd "$path"
pwd
```
Use array `BASH_SOURCE` to get the file name, change to the dir where the file saved and then `pwd`.  

#### 4. get the specific parameter of shell
```
while (( "$#" )); do
case $1 in
        (paramIWant)
          echo ""$1" found"
        ;;
        esac
shift
done
```
Use while to iterate all params and case clause to get the specific param that is wanted.  
execute shell by command `./test.sh 1 2 paramIWant` and get the result `paramIWant`.
#### 5. get all parameters in in shell
method 1: use do-while and if clause
```
param1=""
param2=""
while (( "$#" )); do
    if [ "$1" == "--param1"]; then
        shift
        param1="$1"
    elif [ "$1" == "--param2"]; then
        shift
        param2="$1"
    fi
shift
done
```
method 2: use do-while and case clause
```
param1=""
param2=""
while (( "$#" )); do
    case in ("--param1")
      shift
      param1="$1"
      ;;
    esac
    case in ("--param2")
      shift
      param2="$1"
      ;;
    esca
    shift
done
```
