## Configuration load
### Problem
When there are multiple environments (like QA, UAT and Prod) an Angular app is supposed to be run, multiple environment files are required to be used to provide different settings for the project.  
It is not always a good idea to hard code configs (like Api base urls, server name, attribute name, DBConnectoin string, etc) in a file within an Angular project. For one reason, angular project is usually written in typescript and all TS files need to be transpiled into JS. In this senario, when environemnt changes, TS files need to be transpiled again. Second reason is that a project usually needs to be minified and bundled into one file and it is very hard to modify settings in this file. Without map, project bundling is usually a file with only one single line where a specific text is extremly to be found.  
### Add a new component to add configs
Set one file to specify the environemnt
```
{
  "env": "dev"
}
```
Write different settings in multiple
```
//config.dev.json
{
  "attr": "dev value"
}

//config.qa.json
{
  "attr": "qa value"
}

//config.prod.json
{
  "attr": "prod value"
}
```
Create a serice angular and use http to get env file, code is like below:
```
import { Inject, Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';

class EnvData {
    env: string;
}

@Injectable()
export class AppConfig {
    private config: Object = null;
    private env: Object = null;

    constructor(private http: Http) { }

    private getConfig(key: string) {
        return this.config[key];
    }

    private getEnv(key: string) {
        return this.env[key];
    }

    public load() {
        return new Promise((resolve, reject) => {
            this.http.get('env.json')
                .map(res => res.json())
                .catch((error: any) => {
                    console.log("Config file not found");
                    resolve(true);
                    return Observable.throw(error.json().error || 'Server error');
                }).subscribe((envResponse) => {
                    console.log(envResponse)
                    this.env = envResponse;
                    let request: any = null;
                    let mode = envResponse.env;
                    if (mode == "prod" || mode == "local" || mode == "qa") {
                        request = this.http.get('config.' + mode + '.json');
                    } else {
                        console.error("env.json is invalid, " + mode + " is not recgonized. Please only use prod or dev");
                        resolve(true);
                    }

                    if (request) {
                        request
                            .map(res => res.json())
                            .catch((error: any) => {
                                console.error("error reading the file");
                                resolve(error);
                                return Observable.throw(error.json().error || "server error");
                            })
                            .subscribe((responseData) => {
                                this.config = responseData;
                                resolve(true);
                            });
                    } else {
                        console.error("File not valid");
                        resolve(true);
                    }
                })

        })
    }
}
```
Add the service in app.module and it can be used in other components.
```
providers: [
    ...
    AppConfig,
    { 
      provide: APP_INITIALIZER, 
      useFactory: (config: AppConfig) => () => config.load(), 
      deps: [AppConfig], 
      multi: true 
    }
    ...
]
```

### Pass config in .Net project
In staup, load config files into object configuration.
```
public Startup(IHostingEnvironment env)
{
    var builder = new ConfigurationBuilder()
        .SetBasePath(env.ContentRootPath)
        .AddJsonFile("appsettings.json", optional: true, reloadOnChange: true)
        .AddEnvironmentVariables();
    Configuration = builder.Build();
}
```
Get section in config and read into class, the class should has the same attribute name as appsetttings.json.
```
public void ConfigureServices(IServiceCollection services)
{
    ...
    services.Configure<AngularSettings>(Configuration.GetSection("AngularSettings")); 
    ...
}
```
Pass variables using js in index.cshtml file
```
//in index.cshtml
@inject IOptions<AngularSettings> OptionsApplicationConfiguration
<script>
var angularSettings = {
  attributeName1: '@OptionsApplicationConfiguration.Value.attributeName1',
  attributeName2: '@OptionsApplicationConfiguration.Value.attributeName2',
  attributeName3: '@OptionsApplicationConfiguration.Value.attributeName3',
  attributeName4: '@OptionsApplicationConfiguration.Value.attributeName4',
  ...
}
</script>
```
After variables passed, they can be used directly in Angular components. These variables can be imported into environemnt.ts and all other settings can be generated here be used in all other components.
```
//in environemnt.ts
declare var angularSettings: any;
```
### Conclusion
Always push all settings into config file will make the project deployed and pushed easily.
