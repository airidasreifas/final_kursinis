import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnitTests {

    @Test
    void shouldBulletMove() {
        Bullets bullets = new Bullets(0,0,Direction.U);

        bullets.move();

        Assertions.assertEquals(0,bullets.x);
        Assertions.assertEquals(-12,bullets.y);
    }

    @Test
    void shouldTankMove() {
        Tank myTank = new Tank(300, 560, true, Direction.U, new Main(),1);

        myTank.move();

        Assertions.assertEquals(300,myTank.getX());
        Assertions.assertEquals(554,myTank.getY());
    }

    @Test
    void shouldTankFireBullet() {
        Tank myTank = new Tank(300, 560, true, Direction.STOP, new Main(),1);

        Bullets firedBullet = myTank.fire();

        Assertions.assertNotNull(firedBullet);;
    }
}
