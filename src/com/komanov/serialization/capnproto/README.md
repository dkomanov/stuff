# Cap’n Proto

* https://capnproto.org/
* https://github.com/capnproto/capnproto-java

```
sudo apt install capnproto libcap
```

```
capnpc -ojava -I ~/src/external/capnproto-java/compiler/src/main/schema common.capnp site.capnp events.capnp && mv *.java ../domain/capnproto/
```
