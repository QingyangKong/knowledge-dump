### tip 1
React Component must start with a capitalized letter since lowercase names will be consiered as HTLM tag.  
check link: [ReactJS component names must begin with capital letters?](https://stackoverflow.com/questions/30373343/reactjs-component-names-must-begin-with-capital-letters)
### tip 2
React jsx file cannot print boolean value directly. use `.toString()` to convert boolean to string to display it.  
check link: [ReactJS component names must begin with capital letters?](https://stackoverflow.com/questions/30373343/reactjs-component-names-must-begin-with-capital-letters)
### tip 3
triple dots can be used on an objects to pass attributes to component. For example:
```
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
