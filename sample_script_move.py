from argparse import ArgumentParser
import shutil

#USER PARAMETERS
root = 'C:/Users/{YOU}/' #Put the root of all folders here
dirs = ['{PATH}/{PROJECT}/resources', '{PATH 2}'] #The program will create a jar in each path provided by this list

#CODE
parser = ArgumentParser()
parser.add_argument('jar', help='Jar name')
parser.add_argument('version', help='Program version')
args = parser.parse_args()

for dir in dirs:
	print(root + dir)
	shutil.copyfile('./build/libs/{}-{}.jar'.format(args.jar, args.version), root + dir +'/'+ args.jar +'.jar')