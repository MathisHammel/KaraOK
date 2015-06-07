import socket
Sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
Host = '46.101.167.42' # l'ip locale de l'ordinateur
Port = 8000         # choix d'un port
 
# on bind notre socket :
Sock.bind((Host,Port))
 
# On est a l'ecoute d'une seule et unique connexion :
Sock.listen(1)
 
# Le script se stoppe ici jusqu'a ce qu'il y ait connexion :
client, adresse = Sock.accept() # accepte les connexions de l'exterieur
print "L'adresse",adresse,"vient de se connecter au serveur !"
while 1:
        RequeteDuClient = client.recv(255) # on recoit 255 caracteres grand max
        if not RequeteDuClient: # si on ne recoit plus rien
                break  # on break la boucle (sinon les bips vont se repeter)
        print RequeteDuClient
        Sock.send("SWAAAAG")
