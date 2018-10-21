### tip 1
React Component must start with a capitalized letter since lowercase names will be consiered as HTLM tag.  
check link: [ReactJS component names must begin with capital letters?](https://stackoverflow.com/questions/30373343/reactjs-component-names-must-begin-with-capital-letters)
### tip 2
React jsx file cannot print boolean value directly. use `.toString()` to convert boolean to string to display it.  
check link: [Cannot render boolean value in JSX?](https://stackoverflow.com/questions/38337262/cannot-render-boolean-value-in-jsx)
### tip 3
triple dots can be used on an objects to pass attributes to component. For example:
```javascript
todos = [
    {
        id: 113333,
        text: 'Go Shopping',
        complete: false
    }, 
    {
        id: 1231233,
        text: 'pay bills',
        complete: false
    }
];
...

class Todo extends React.Component {
    render() {
        return (
            <div>
                ID: {this.props.id} <br />
                Text: {this.props.text} <br />
                Complete: {this.props.complete.toString()} <br /><br />
            </div>
        );
    };
}
export default Todo;
...

<Todo key={todo.id} {...todo}/>
```
### tip 4
Module syntax difference:  
There are 3 types of modules syntax: CommonJS, AMD and ES6. Node is using CommonJS.   
ES6:
```javascript
export module_name;
import sth from module_name;
```
CommonJS:
```javascript
module.exports module_name;
cosnt sth = require(module_name);
```
It is not allowed to use `import` or `export` in node js. Babel is used to transpile different versions of module syntax to ES6.
