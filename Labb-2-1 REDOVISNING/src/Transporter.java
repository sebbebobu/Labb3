/**
 * Created by brandts on 2018-02-07.
 */
public interface Transporter<T> {
    void attachVehicle(T type);
    void detachVehicle(T type);
    void detachVehicle();
    boolean canAttach(T type);
}
