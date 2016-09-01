$(document).ready(function(argument) {
  var container = $("#table-container"),
      ltTable = container.find(".table-lt"),
      topTable = container.find(".table-top"),
      leftTable = container.find(".table-left"),
      rightTable = container.find(".table-right"),

      containerWidth = 0,
      containerHeight =0,

      ltTableWidth = 0,
      ltTableHeight = 0,

      widthJson = {},

      timerLT = null;

  ltTableWidth = ltTable.width();
  ltTable.width(ltTableWidth);
  topTable.css("marginLeft",ltTableWidth);
  leftTable.width(ltTableWidth);
  rightTable.css("marginLeft",ltTableWidth);
  ltTableHeight = ltTable.height();
  leftTable.css("marginTop",ltTableHeight+"px");
  rightTable.css("marginTop",ltTableHeight+"px");

  containerHeight = container.height();
  containerWidth = container.width();
  topTable.width(containerWidth - ltTableWidth - (container.innerWidth() - container[0].clientWidth));
  leftTable.height(containerHeight - ltTableHeight - (container.innerHeight() - container[0].clientHeight));

  // figure out the width of each DIV in TD  --start
  function setDivWidth(obj){
    $(obj).find("div").each(function(index){
      if(!widthJson[index]){
        widthJson[index] = 0;
      }
      if(widthJson[index]<$(this).width()) {
        widthJson[index] = $(this).width();
      }
    }); 
  }
  topTable.find("tr").each(function(){
    setDivWidth(this);
  });
  rightTable.find("tr").each(function(){
    setDivWidth(this);
  });

  topTable.find("tr:first div").each(function(index){
    $(this).width(widthJson[index]);
  });
  rightTable.find("tr:first div").each(function(index){
    $(this).width(widthJson[index]);
  });
  // figure out the width of each DIV in TD  --end

  container.scroll(function(){
    var currentScrollTop = $(this).scrollTop(),
        currentScrollLeft = $(this).scrollLeft();
    topTable.find(".table-mask").css("left", -currentScrollLeft+"px");
    leftTable.find(".table-mask").css("top", -currentScrollTop+"px");

  });

  $(document).scroll(function(){
    var currentScrollTop = $(this).scrollTop(),
        currentScrollLeft = $(this).scrollLeft();
    ltTable.css("marginTop", -currentScrollTop+"px");
    ltTable.css("marginLeft", -currentScrollLeft+"px");
    topTable.css("marginTop", -currentScrollTop+"px");
    topTable.css("marginLeft", ltTableWidth - currentScrollLeft+"px");
    leftTable.css("marginTop", ltTableHeight - currentScrollTop+"px");
    leftTable.css("marginLeft", -currentScrollLeft+"px");

  });

  // for IE, when window's scrollbar is moved, keep ltTable,leftTable,topTable in the correct position
  $(window).resize(function(){
    $(document).scroll();
  });

  //reset the width of topTable and the height of leftTable when container's size is changed, if container's size is fixed, you can skip the code below.
  timerLT = setInterval(function(){
    if(containerWidth != container.width() || containerHeight != container.height()){
      topTable.width(container.width() - ltTableWidth - (container.innerWidth() - container[0].clientWidth));
      leftTable.height(container.height() - ltTableHeight - (container.innerHeight() - container[0].clientHeight));

      containerWidth = container.width();
      containerHeight = container.height();

      container.scroll();// for IE
      
    };
             
  },0);

});