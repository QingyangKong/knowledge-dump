# Client Side Routers
## What is CSR?
It is not very easy to understand the router mechanism used by front-end frameworks. You always needs to write some routes to control components in front-end framework without sending requests to back-end. Sample codes are like below, for example, in React:
```xml
<Switch>
    <Route path='/body' component={MyRouterBody}></Route>
    <Route path='/head' component={MyRouterHead}></Route>
</Switch>
```
In the scenario, you want to hit "localhost:xxxx/body" to get the component `MyRouterBody`. Let's see what happens after you input "localhost:xxxx/body" in your browser? As experience telling us, the browser will make a REST GET request to url "localhost:xxxx/body". If so, how this works? Because we do not even have a server to respond requests, it should definitely not work. By using router. the magic makes your framework work is called CSR(Client Site Router), it is a way handling all input urls in browser and then render different pages, views, components or whatever you call it to in browser without sending requests to server side.   

## How CSR works?
The way CSR works is to manually change url you input and using browser Api `history.pushState()` and then render the view without a page refresh. So actually all logics you are supposed to use needs to be downloaded when you visit the page, and routers are included in the logics. After the logics downloaded, router contained in the code will process all of your input urls.  

## What is the problem in CSR?
Here is the problem: as said before, user must download all js codes first and then routers will work, but your browser will make the GET request before you loading all js codes. So sometimes, you got the error: `Cannot GET /url` if the app is not configured properly.  

## How to solve it?
The solution is to force all urls to the fixed url and sample solution of for React is posted below. In Webpack-development-server, you need to force all server requests to `index.html` by modifying the config file `webpack.config.js`.
```json
var config = {
	entry: './main.js',
	output: {
		path: '/',
		filename: 'index.js',
		publicPath: '/',
	},
	devServer: {
		inline: true,
		port: 8080,
		historyApiFallback: true,
	},
	module: {
		rules: [
			{
				test: /\.jsx?$/,
				exclude: /node_modules/,
				loader: 'babel-loader',
				query: {
					presets: ['es2015', 'react']
				}
			}
		]
	}
}
module.exports = config;

```
`publicPath` allows you to specify the base path for all the assets within your application.   
`historyApiFallback` will redirect 404s to /index.html.

Please Check this article below for more information:  
[Fixing the "cannot GET /URL" error on refresh with React Router (or how client side routers work)](https://tylermcginnis.com/react-router-cannot-get-url-refresh/)
