insert into tb_usuario(id,username,password,enabled) values(1,"nahyah","$2a$04$vIhdoyHNctF6ycFM1RnzAeTCXVTtORiorbkdMZc1hWNv86mBlbGvC",true);
insert into tb_usuario(id,username,password,enabled) values(2,"raphael","$2a$04$vIhdoyHNctF6ycFM1RnzAeTCXVTtORiorbkdMZc1hWNv86mBlbGvC",true);
insert into tb_usuario(id,username,password,enabled) values(3,"stefany","$2a$04$vIhdoyHNctF6ycFM1RnzAeTCXVTtORiorbkdMZc1hWNv86mBlbGvC",true);

insert into tb_rol(authority,descripcion,usuario_id) values("ROLE_ADMIN","Soy administrador",1);
insert into tb_rol(authority,descripcion,usuario_id) values("ROLE_ADMIN","Soy administrador",2);
insert into tb_rol(authority,descripcion,usuario_id) values("ROLE_USER","Soy usuario",2);
insert into tb_rol(authority,descripcion,usuario_id) values("ROLE_USER","Soy usuario",3);
