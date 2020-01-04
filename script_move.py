from argparse import ArgumentParser
import shutil

#USER PARAMETERS
root = 'C:/Users/Ec/'
dirs = ['git/Fatal Strike/resources', 'git/nazaraapi/resources']

#CODE
parser = ArgumentParser()
parser.add_argument('jar', help='Jar name')
parser.add_argument('version', help='Program version')
args = parser.parse_args()

print(shutil.os.path.curdir)

for dir in dirs:
    shutil.copyfile('./build/libs/{}-{}.jar'.format(args.jar, args.version), root + dir +'/'+ args.jar +'.jar')