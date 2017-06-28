/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EsameApp.Services;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 *
 * @author tss
 */
@Singleton
public class CheckAuction {
    
    @Schedule(second = "*/1",minute = "*",hour = "*")
    public void test(){
        System.out.println("exec.." + System.currentTimeMillis());
    }
}
