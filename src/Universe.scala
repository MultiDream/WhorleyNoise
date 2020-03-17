class Universe()
{
    val partitionSize : Int = 32;
    val dimension = new Array[Int](2);
    dimension(0) = 20;
    dimension(1) = 20;
    var chunks : Array[Array[Chunk]] = Array.ofDim[Chunk](dimension(0),dimension(1));

    for{
        x <- 0 until dimension(0)
        y <- 0 until dimension(1)
    }{
        chunks(x)(y) = new Chunk();
    }
    // Draws the universe's array of Chunks.
    def drawSpace(): Unit ={
        var pixels = Array.ofDim[Pixel](dimension(0) * partitionSize, dimension(1) * partitionSize);
        for{
            // Works for rectangular spaces only.
            x <- 0 until pixels.length
            y <- 0 until pixels(0).length
        }{
            // Get Nearest Focus.
            val point = Vector2(x,y); // Vec2 representation of the Pixel's location.
            var nearestFocus = getNearestFocus(point);

            // Calculate distance to focus.
            val distance : Float = point.distanceTo(nearestFocus);

            // Set the color.
            pixels(x)(y) = distanceToColorFunc(distance);
        }

        //Draw all pixels.
        new Drawer().drawScreen(pixels);
    }

    def getNearestFocus(point: Vector2): Vector2 ={
        // Find the partition of Vector 2.
        var row : Int = (point.x / partitionSize).floor.asInstanceOf[Int];
        var column: Int =  (point.y/partitionSize).floor.asInstanceOf[Int];

        var closestFocus: Vector2 = null;
        var distance: Float = Float.MaxValue;
        for {
            i <- ( row-2 max 0) until (row+2 min dimension(0)-1)
            j <- ( column-2 max 0) until (column+2 min dimension(1)-1)
        }{
            var chunk = chunks(i)(j);
            var candidateFocus : Vector2 = chunk.focus;

            // Focus is currently local. Must transform into universal coords.
            candidateFocus = candidateFocus * (partitionSize) // Scale into local partition size.
            candidateFocus = candidateFocus + Vector2(partitionSize * i, partitionSize * j); // transform to partition base coordinates.

            var candidateDist : Float = point.distanceTo(candidateFocus)
            if ( candidateDist < distance){
                closestFocus = candidateFocus;
                distance = candidateDist;
            }
        }
        return closestFocus

        // Check all partitions within a 5x5 block around the partition.
    }

    def distanceToColorFunc(distance: Float): Pixel = {
        var grey: Int = (255-distance).floor.asInstanceOf[Int];
        if (grey > 255){
            grey = 255
        } else if (grey < 0){
            grey = 0
        }
        val pixel = Pixel(grey,grey,grey);
        return pixel;
    }

}
