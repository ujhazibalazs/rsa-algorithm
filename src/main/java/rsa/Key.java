package rsa;

import lombok.Data;

@Data
public class Key {

    private int p;
    private int q;
    private int n;
    private int phi_n;
    private int e;
    private int d;

}
