# Client Side Routers
## What is CSR?
It is not easy to understand the router mechanism used by front-end framework. When you write some routes to control components in front-end framework, for example, React like this:
```
<Switch>
    <Route path='/body' component={MyRouterBody}></Route>
    <Route path='/head' component={MyRouterHead}></Route>
</Switch>
```
Then you hit "localhost:xxxx/body", what happens? As experience telling us, the browser will make a REST GET request to url "localhost:xxxx/body". If so, how this works? Because we do not even have a server to respond requests, it should definitely not work. There is a thing called CSR(Client Site Router) used to handle all routes we input in browser and then render different pages, views, components or whatever you call it to in browser.   

## How CSR works?
The way CSR works is to manually change url you input and using browser Api `history.pushState()` and then render the view without a page refresh. So actually all logics you are supposed to use needs to be downloaded when you visit the page, and routers are included in the logics. After the logics downloaded, routers will handle all of your urls.  

## What is the problem in CSR?
Here is the question. As said before, user must download all js code first and then routers will work but your browser will make the GET request before doing anything. So sometimes, you got the error: `Cannot GET /url` if the app is not configured properly.  

## how to solve it?
Because I am using react with webpack-development-server, I post the solution of this development environment here:  
In Webpack-development-server, you need to force all server requests to `index.html` by modifying the config file `webpack.config.js`
```
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
