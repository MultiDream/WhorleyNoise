case class Pixel(r: Int, g: Int, b:Int)
{
    def +(that: Pixel) = {
        val newR = limitChannel(this.r + that.r)
        val newG = limitChannel(this.r + that.r)
        val newB = limitChannel(this.r + that.r)
        Pixel(newR,newG,newB);
    };

    def limitChannel(value: Int): Int ={
        if (value > 255){
            return 255;
        } else if (value < 0){
            return 0;
        } else {
            return value
        }
        return 0;
    }

}
