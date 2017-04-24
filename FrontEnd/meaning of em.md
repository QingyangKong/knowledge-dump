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

<!--question 1: what is width and height of first <div>?
Because the default font-size is 16px, width and height of the first div are 160px and 160px.

question 2: what is width and height of <div> in the second <div>?
Because the font-size in the parent docuemnt is set as 10px, 1em is 10px. Width and height are 100px and 100px respectively.

question 2: what is width and height of third <div>
Becasuse font-size is defined in child DOM of the thrid div, width and height is still 160px and 160px.

Advantage of using 'em' is that the size of element can be adjusted based on the document font size.-->
</html>
```
