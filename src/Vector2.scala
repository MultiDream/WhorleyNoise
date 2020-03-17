import scala.math;

case class Vector2( x: Float, y: Float)
{
    def +(that: Vector2) = Vector2(this.x + that.x,this.y + that.y);

    def *(that: Float) = Vector2(this.x * that, this.y * that);
    def *(that: Int) = Vector2(this.x * that, this.y * that);

    def distanceTo(that: Vector2) : Float ={
        var distance: Float = 0;
        var x2 = scala.math.pow(x-that.x,2);
        var y2 = scala.math.pow(y-that.y,2);
        distance = (scala.math.sqrt(x2 + y2)).asInstanceOf[Float];
        return distance;
    };

    def normalized(): Vector2 = ???
}
