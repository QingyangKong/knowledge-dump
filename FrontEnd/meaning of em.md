```
<html>
    <h1>test</h1>
    <div style="border: solid black; width: 10em; height:10em">
    </div>

    <div style="font-size: 10px">
        <div style="border: solid black; width: 10em; height: 10em">
            <span style="font-size: 1em">in span<span>
        </div>
    </div>

    <div style="border: solid black; width: 10em; height: 10em">
        <span style="font-size: 30px">font in the second div</span>
    </div>
    
    <div style="font-size: 10px">
        <span style="font-size: 10em">font_size_1</span>
        <div style="font-size: 15px">
            <span style="font-size: 10em">font_size_2</span>
        </div>
    </div>
</html>
```

### question 1: what is width and height of first `<div>`?  
Because the default font-size is 16px, width and height of the first div are 160px and 160px.

### question 2: what is width and height of `<div>` in the second `<div>`?  
Because the font-size in the parent docuemnt is set as 10px, 1em is 10px. Width and height are 100px and 100px respectively.

### question 3: what is width and height of third `<div>`?  
Becasuse font-size is defined in child DOM of the thrid div, width and height is still 160px and 160px.

### question 3: what is font size of `font_size_1` and `font_size_2`?
Same as `div`, font-size is decided by parent DOM font-size and em, so the `font_size_1` is 100px and `font_size_2` is 150px.

### Using 'em' is able to make size of element can be adjusted by parent document font size, while it is not always good for DOM element that needs to be set specifically.
