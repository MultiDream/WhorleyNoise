class Drawer
{
    def drawScreen(screen : Array[Array[Pixel]]){
        for{
            x <- 0 until screen.length
            y <- 0 until screen(0).length
        }{
            drawPixel(Vector2(x,y),screen(x)(y));
        }
    }

    def drawPixel(coords : Vector2, pixel: Pixel): Unit ={
        // Implement in Scala Fiddle.
    }
}
