extern crate rand;

use rand::Rng;
use std::io;

fn main() {
    let mut rng = rand::thread_rng();
    let mut counter = 0;
    /*  zero = castle kills messenger at start.
        Neither Army attacks
        Draw 100%
    */  
    let mut zero = 0;
    /*  one = Messenger delivers message and castle kills messenger on return.
        Army B attacks
        Army A doesn't attack
        Loss 100%
    */
    let mut one = 0;
    /*  two = messenger delivers message and makes it back.
        Army B attacks
        Army A attacks
        Win 100%  
    */
    let mut two = 0;
    let mut num_of_runs;
    loop{
       num_of_runs = get_input();
       if num_of_runs != 0 {
           break;
       }
       println!("Your input is not a valid integer!");
    }
    while counter < num_of_runs {
        let temp = rng.gen_range(0,3);
        if temp == 0 {
            zero += 1;
        }
        else if temp == 1 {
            one += 1;
        }
        else {
            two += 1;
        }
        counter += 1;
    }
    
    println!("Zero\tOne\tTwo\n{}\t{}\t{}", zero, one, two);
    println!("{:.2}%\t{:.2}%\t{:.2}%",  (zero as f32 / num_of_runs as f32) * 100.0,
                                        (one as f32 / num_of_runs as f32) * 100.0,
                                        (two as f32 / num_of_runs as f32) * 100.0);
}

fn get_input() -> usize {
    let mut run_time = String::new();
    println!("Please enter a valid integer:");
    io::stdin()
        .read_line(&mut run_time)
        .expect("failed to read from stdin");

    let num_of_runs = match run_time.trim().parse::<usize>() {
        Ok(num_of_runs) => num_of_runs,
        Err(_) => 0,
    };

    num_of_runs
}