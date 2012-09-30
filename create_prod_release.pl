use strict;

my $release_string = shift @ARGV;
defined $release_string or die "usage: create_prod_release.pl VERSION_STRING";

my $release_dir = "out/TokenLab.$release_string";
print `mkdir $release_dir`;
print `mkdir $release_dir/jars`;
print `mkdir $release_dir/images`;
print `mkdir $release_dir/tokens`;
print `cp out/artifacts/TokenLab_jar/*.jar $release_dir/jars`;

my @distro_files = ( "log4j.properties" , "TokenLab.bat" , "TokenLab.sh" );
my @resources = ( "LibTokenLab.rptok" , "PathfinderCampaignProperties.txt" , "Tokenlab-Properties.mtprops",
                  "TokenLab.cmpgn" , "TokenLabImageTable.mttable" );

foreach my $distro_file ( @distro_files ) {
    print `cp distro/$distro_file $release_dir`;
}

foreach my $resource ( @resources ) {
    print `cp src/net/sozinsoft/tokenlab/res/$resource $release_dir`;
}

#print "zip -r $release_dir/ TokenLab.$release_string";
#my $orig_zip = "$release_dir/TokenLab.$release_string/.zip";
#my $new_zip  = "out/TokenLab.$release_string.zip";
#print `mv $orig_zip $new_zip`;
#print `cp $new_zip out/TokenLab-latest.zip`;

