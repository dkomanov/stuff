extern crate greeter;

fn main() {
    let hello = greeter::Greeter::new("Hello");
    hello.greet("world");
}
